package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * jpabook.jpashop.api OrderSimpleApiController
 * xtoOne에서 성능 최적화
 * order
 * order->member
 * order->delivery
 * @author : K
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

  private final OrderRepository orderRepository;

  //무한 루프에 빠진다 order에서 member를 찾고 member에서 order를 찾고
  @GetMapping("/api/v1/simple-orders")
  public List<Order> ordersv1(){
    List<Order> all = orderRepository.findAllByString(new OrderSearch());
    for (Order order : all) {
      order.getMember().getName(); //Lazy 강제 초기화
      order.getDelivery().getAddress(); // Lazy 강제 초기화
    }
    return all;
  }

}
