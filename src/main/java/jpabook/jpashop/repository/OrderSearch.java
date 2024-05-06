package jpabook.jpashop.repository;

import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * jpabook.jpashop.repository OrderSearch
 *
 * @author : K
 */
@Getter
@Setter
public class OrderSearch {
  private String memberName;
  private OrderStatus orderStatus;
}
