package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
*
jpabook.jpashop.service
OrderServiceTest
*
@author : K
*/
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

  @Autowired EntityManager em;
  @Autowired OrderService orderService;
  @Autowired OrderRepository orderRepository;

  @Test
  void 주문() {
    Member member = createMember();
    
    Book book = createBook("JPA 한번에 알기", 10000, 10);
    
    int orderCount = 2;
    Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
    
    Order getOrder = orderRepository.findOne(orderId);
    
    assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문 시 상태는 ORDER");
    assertEquals(1,getOrder.getOrderItems().size(),"주문 상품 종류수가 정확해야한다");
    assertEquals(10000 * orderCount,getOrder.getTotalPrice(), "주문 가격은 가격*수량이다");
    assertEquals(8,book.getStockQuantity(), "주문 수량만큼 재고가 줄어야 한다");
    
  }
  
  @Test
  void 재고수량초과() {
    Member member = createMember();
    Item item = createBook("JPA 한번에 알기", 10000, 10);

    int orderCount = 11;
    
    assertThrows(NotEnoughStockException.class, () -> {
      orderService.order(member.getId(), item.getId(), orderCount);
    });
  }
  
  @Test
  void 주문취소() {
    Member member = createMember();
    Book item = createBook("JPA 한번에 알기", 10000, 10);

    int orderCount = 2;
    
    Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
    
    orderService.cancelOrder(orderId);
    
    Order getOrder = orderRepository.findOne(orderId);
    
    assertEquals(OrderStatus.CANCEL, getOrder.getStatus(),"주문 취소 상태는 cancel이다");
    assertEquals(10, item.getStockQuantity(),"취소된 상품은 그만큼 재고 증가");
    
  }
  private Book createBook(String name, int price, int quantity) {
    Book book = new Book();
    book.setName(name);
    book.setPrice(price);
    book.setStockQuantity(quantity);
    em.persist(book);
    return book;
  }
  
  private Member createMember() {
    Member member = new Member();
    member.setName("회원1");
    member.setAddress(new Address("서울","도래울로","123-321"));
    em.persist(member);
    return member;
  }
}