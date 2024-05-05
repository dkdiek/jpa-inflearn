package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * jpabook.jpashop.repository OrderRepository
 *
 * @author : K
 */
@Repository
@RequiredArgsConstructor
public class OrderRepository {
  private final EntityManager em;

  public void save(Order order) {
    em.persist(order);
  }

  public Order findOne(Long id) {
    return em.find(Order.class, id);
  }

//  public List<Order> findOrders(OrderSearch orderSearch) {
//    return orderRepository.findAll(orderSearch);
//  }
}
