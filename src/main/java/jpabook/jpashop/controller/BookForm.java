package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

/**
 * jpabook.jpashop.controller BookForm
 *
 * @author : K
 */
@Getter
@Setter
public class BookForm {
  private Long id;
  private String name;
  private int StockQuantity;
  private int price;
  private String author;
  private String isbn;
}
