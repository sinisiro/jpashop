package com.sinisiro.jpashop.service.order;

import com.sinisiro.jpashop.domain.Order;
import com.sinisiro.jpashop.domain.OrderSearch;
import com.sinisiro.jpashop.domain.OrderStatus;
import com.sinisiro.jpashop.repository.OrderQueryRepository;
import com.sinisiro.jpashop.repository.OrderRepository;
import com.sinisiro.jpashop.repository.order.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrderRepository orderRepository;

    public List<OrderDto> ordersV2_inline() {
        List<Order> orders = orderRepository.findAll(new OrderSearch("", OrderStatus.ORDER));
        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return result;
    }
}
