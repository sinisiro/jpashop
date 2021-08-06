package com.sinisiro.jpashop.api;

import com.sinisiro.jpashop.domain.*;
import com.sinisiro.jpashop.repository.OrderQueryRepository;
import com.sinisiro.jpashop.repository.OrderRepository;
import com.sinisiro.jpashop.repository.order.OrderQueryDto;
import io.swagger.annotations.Api;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@Api(tags = { "2. OrderApiController" }, description = "주문 정보(최적화)") // Swagger 최상단 Controller 명칭
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    /**
     * V1. 엔티티 직접 노출
     * - Hibernate5Module 모듈 등록, LAZY=null 처리 * - 양방향 관계 문제 발생 -> @JsonIgnore
     */
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAll(new OrderSearch("", OrderStatus.ORDER));

        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().forEach(o -> o.getItem().getName());
        }
        return all;
    }

    /**
     V2. 엔티티를 조회해서 DTO로 변환(fetch join 사용X)
    * - 트랜잭션 안에서 지연 로딩 필요
     * */
    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAll(new OrderSearch("", OrderStatus.ORDER));
        List<OrderDto> result = orders.stream()
                .map(o->new OrderDto(o))
                .collect(Collectors.toList());

        return result;
    }
    /**
    *   V3. 엔티티를 조회해서 DTO로 변환(fetch join 사용O)
    * - 페이징 시에는 N 부분을 포기해야함(대신에 batch fetch size? 옵션 주면 N -> 1 쿼리로 변경
    가능) */
    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();
        List<OrderDto> result = orders.stream()
                .map(o-> new OrderDto(o))
                .collect(Collectors.toList());
        return result;

    }

    /**
     * V3.1 엔티티를 조회해서 DTO로 변환 페이징 고려
     *-ToOne 관계만 우선 모두 페치 조인으로 최적화
     * - 컬렉션 관계는 hibernate.default_batch_fetch_size, @BatchSize로 최적화 */
    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_page(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                        @RequestParam(value = "limit", defaultValue
                                                = "100") int limit) {
        List<Order> orders = orderRepository.findAllWithMemberDelivery2(offset, limit);
        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(toList());
        return result;
    }

    /**
     * *V4.JPA에서 DTO로 바로 조회, 컬렉션 N 조회 (1+NQuery)
     * * - 페이징 가능
     */
    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4() {
        return orderQueryRepository.findOrderQueryDtos();
    }

    /*
    주문 조회 V5: JPA에서 DTO 직접 조회 - 컬렉션 조회 최적화
     */
    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> ordersV5() {
        return orderQueryRepository.findAllByDto_optimization(); }



    @Data
    static class OrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate; //주문시간
        private OrderStatus orderStatus;
        private HomeAddress address;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
            orderItems = order.getOrderItems().stream()
                    .map(orderItem -> new OrderItemDto(orderItem)).collect(toList());
        }

        @Data
        static class OrderItemDto {
            private String itemName;//상품 명
            private int orderPrice; //주문 가격
            private int count; //주문 수량

            public OrderItemDto(OrderItem orderItem) {
                itemName = orderItem.getItem().getName();
                orderPrice = orderItem.getOrderPrice();
                count = orderItem.getCount();
            }
        }
    }
}
