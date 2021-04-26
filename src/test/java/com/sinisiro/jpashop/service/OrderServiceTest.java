package com.sinisiro.jpashop.service;

import com.sinisiro.jpashop.domain.*;
import com.sinisiro.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //Given
        Member member = createMember();
        Item item = createBook("JPA", 10000, 10);
        int orderCount = 2;

        //When
        Long orderID = orderService.order(member.getId(), item.getId(), orderCount);

        //Then
        Order getOrder = orderRepository.findOne(orderID);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER,
                getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1,
                getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 10000 * 2,
                getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, item.getStockQuantity());
    }

    private Item createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("맹수");
        member.setAddress(new HomeAddress("서울", "경기", "123"));
        em.persist(member);
        return member;
    }

}