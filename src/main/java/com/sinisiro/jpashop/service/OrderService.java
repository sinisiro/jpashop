package com.sinisiro.jpashop.service;

import com.sinisiro.jpashop.domain.*;
import com.sinisiro.jpashop.repository.ItemRepository;
import com.sinisiro.jpashop.repository.MemberRepository;
import com.sinisiro.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    public Long order(Long memberId, Long itemId, int count) {

        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문생성
        Order order = Order.createOrder(member,delivery,orderItem);

        //주문 저장
        orderRepository.save(order);
        return  order.getId();
    }

    /**
     * 주문취소
     */
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);

        order.cancle();
    }

}
