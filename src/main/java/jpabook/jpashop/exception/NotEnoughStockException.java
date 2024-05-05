package jpabook.jpashop.exception;

/**
 * jpabook.jpashop.exception NotEnoughStockException
 *
 * @author : K
 */
public class NotEnoughStockException extends RuntimeException {

  public NotEnoughStockException() {
    super();
  }

  public NotEnoughStockException(String message) {
    super(message);
  }

  public NotEnoughStockException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotEnoughStockException(Throwable cause) {
    super(cause);
  }
}
