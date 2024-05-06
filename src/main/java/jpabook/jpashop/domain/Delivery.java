package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * jpabook.jpashop.domain Delivery
 *
 * @author : K
 */
@Entity
@Getter
@Setter
public class Delivery {
  @Id @GeneratedValue private Long id;

  @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
  @JsonIgnore
  private Order order;

  @Embedded private Address address;

  @Enumerated(EnumType.STRING)
  private DeliveryStatus status; // ready, comp
}
