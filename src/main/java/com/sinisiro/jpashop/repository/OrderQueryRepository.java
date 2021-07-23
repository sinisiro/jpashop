package com.sinisiro.jpashop.repository;

import com.sinisiro.jpashop.repository.order.OrderItemQueryDto;
import com.sinisiro.jpashop.repository.order.OrderQueryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderQueryRepository {

    private final EntityManager em;

    /**v4
     * 컬렉션은 별도로 조회
     * Query: 루트 1번, 컬렉션 N 번 * 단건 조회에서 많이 사용하는 방식 */
    public List<OrderQueryDto> findOrderQueryDtos() {
        
        List<OrderQueryDto> result = findOrders();

        result.forEach(o->{
            log.info("[조회]"+o.getOrderId());
            List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
            o.setOrderItems(orderItems);
        });
        return result;
    }
    /**
     * 1:N 관계(컬렉션)를 제외한 나머지를 한번에 조회
     */
    private List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return em.createQuery(
                "select new com.sinisiro.jpashop.repository.order.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
                        " from OrderItem oi" +
                        " join oi.item i" +
                        " where oi.order.id = : orderId",
                OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }
    private List<OrderQueryDto> findOrders() {
        return em.createQuery(
        "select new com.sinisiro.jpashop.repository.order.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
        " from Order o" +
        " join o.member m" +
        " join o.delivery d", OrderQueryDto.class)
            .getResultList();

    }
//OrderQueryRepository에 추가 /**

    /** 최적화
    * Query: 루트 1번, 컬렉션 1번
    * 데이터를 한꺼번에 처리할 때 많이 사용하는 방식
     *  v4/v5 차이는 MAP을 사용해서 매칭 성능 향상(O(1))
    */
    public List<OrderQueryDto> findAllByDto_optimization() {
        List<OrderQueryDto> result = findOrders();

        //인자별 쿼리결과를 담은뒤 출력해줌
        Map<Long, List<OrderItemQueryDto>> orderItemMap = findOrderItemMap(toOrderIds(result));
        result.forEach((o->o.setOrderItems(orderItemMap.get(o.getOrderId()))));

        return result;
    }

    private List<Long> toOrderIds(List<OrderQueryDto> result){
        log.info("[stream]:"+String.valueOf(result.stream()));
        log.info("[stream3]:"+String.valueOf(result.stream().map(o->o.getOrderId()).collect(Collectors.toList())));//[4, 11]
        //list를 직렬화 = stream
        return result.stream()
                .map(o->o.getOrderId())
                .collect(Collectors.toList());
    }

    private Map<Long, List<OrderItemQueryDto>>  findOrderItemMap(List<Long> orderIds){
        List<OrderItemQueryDto> orderItems = em.createQuery(
                "select new com.sinisiro.jpashop.repository.order.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice,oi.count)"+
                        " from OrderItem oi" +
                        " join oi.item i" +
                        " where oi.order.id in :orderIds", OrderItemQueryDto.class)
                .setParameter("orderIds", orderIds) .getResultList();

        return orderItems.stream()
                .collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));

    }
}
