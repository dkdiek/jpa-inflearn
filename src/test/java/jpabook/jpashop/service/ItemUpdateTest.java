package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
 * jpabook.jpashop.service MemberServiceTest
 *
 * @author : K
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class ItemUpdateTest {

  
  @Autowired EntityManager em;

  @Test
  public void updateTest() throws Exception {
    Book book = em.find(Book.class, 1L);
    //tx
    book.setName("asdfasdf");
    //변경감지
    
  }

}
