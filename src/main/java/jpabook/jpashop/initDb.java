package jpabook.jpashop;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * jpabook.jpashop initDb
 *
 * @author : K
 */
@Component
@RequiredArgsConstructor
public class initDb {
  private final InitService initService;

  @PostConstruct
  public void init() {
    initService.dbInit1();
    initService.dbInit2();
  }
  
  @Component
  @Transactional
  @RequiredArgsConstructor
  static class InitService {
    private final EntityManager entityManager;
    public void dbInit1() {
      Member member = createMember("userA", "1","!","1");
      entityManager.persist(member);
      
      Book book1 = createBook("JPA1 book",10000,100);
      entityManager.persist(book1);
      
      Book book2 = createBook("JPA2 book",10000,100);
      entityManager.persist(book2);
      
      OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
      OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
      
      Delivery delivery = new Delivery();
      delivery.setAddress(member.getAddress());
      Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
      entityManager.persist(order);
    }
    
    private static Book createBook(String name, int price, int quantity) {
      Book book1 = new Book();
      book1.setName(name);
      book1.setPrice(price);
      book1.setStockQuantity(quantity);
      return book1;
    }
    
    public void dbInit2() {
      Member member = createMember("userB", "2","2","2");
      entityManager.persist(member);
      
      Book book1 = createBook("SPRING1 book",20000,200);
      entityManager.persist(book1);
      
      Book book2 = createBook("SPRING2 book",40000,400);
      entityManager.persist(book2);
      
      OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
      OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
      
      Delivery delivery = new Delivery();
      delivery.setAddress(member.getAddress());
      Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
      entityManager.persist(order);
    }
    
    private static Member createMember(String name, String city, String street, String zipcode) {
      Member member = new Member();
      member.setName(name);
      member.setAddress(new Address(city, street, zipcode));
      return member;
    }
  }
}
