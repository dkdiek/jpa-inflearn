package jpabook.jpashop.repository.order.simplequery;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * jpabook.jpashop.repository.order.simplequery OrderSimpleQueryRepository
 *
 * @author : K
 */
@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

  private final EntityManager em;

  // dto 에서 컬럼을 지정해서 원하는 컬럼만 가져옴 v3 fetchjoin은 모두 가져왔었음
  public List<OrderSimpleQueryDto> findOrderDtos() {
    return em.createQuery(
            "select new jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address) "
                + " from Order o "
                + "join o.member m "
                + "join o.delivery d",
            OrderSimpleQueryDto.class)
        .getResultList();
  }
}
