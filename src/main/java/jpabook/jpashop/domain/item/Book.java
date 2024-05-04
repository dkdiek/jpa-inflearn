package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * jpabook.jpashop.domain Book
 *
 * @author : K
 */

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
public class Book extends Item {
  private String author;
  private String isbn;
}
