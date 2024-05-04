package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * jpabook.jpashop.domain Album
 *
 * @author : K
 */
@Entity
@DiscriminatorValue("A")
@Getter
@Setter
public class Album  extends Item{
  private String artist;
  private String etc;
}
