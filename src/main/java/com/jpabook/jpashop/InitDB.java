package com.jpabook.jpashop;


import com.jpabook.jpashop.domain.*;
import com.jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;


    @PostConstruct
    public void init() {
        initService.dbInit();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {


        private final EntityManager em;

        public void dbInit() {

            Member member = createMember("kim", "서울", "1", "1111");
            em.persist(member);

            Book book1 = createBook("JPA1 BOOK", 10000, 200);
            em.persist(book1);

            Book book2 = createBook("JPA2 BOOK", 20000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10900, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20900, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order =Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }

        public void dbInit2() {

            Member member = createMember("park", "진주", "2", "1112");
            em.persist(member);

            Book book1 = createBook("Spring1 BOOK", 20000, 100);
            em.persist(book1);

            Book book2 = createBook("Spring2 BOOK", 40000, 300);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20900, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40900, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }

        private Book createBook(String s, int i, int i2) {
            Book book1 = new Book();
            book1.setName(s);
            book1.setPrice(i);
            book1.setStockQuantity(i2);
            return book1;
        }

        private  Member createMember(String name,String a, String s, String s2) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(a, s, s2));
            return member;
        }
    }


}
