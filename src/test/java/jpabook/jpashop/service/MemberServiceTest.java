package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
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
@Transactional
class MemberServiceTest {

  @Autowired MemberService memberService;
  @Autowired MemberRepository memberRepository;
  @Autowired EntityManager em;

  @Test
//  @Rollback(value = false)
  public void 회원가입() throws Exception {
    //given
    Member member = new Member();
    member.setName("kim");
    // when
    Long savedId = memberService.join(member);
    //then
//    em.flush();
    Assertions.assertEquals(member, memberRepository.findOne(savedId));
  }

  @Test
  public void 중복_회원_예외() throws Exception {
    Member member1 = new Member();
    member1.setName("member1");
    
    Member member2 = new Member();
    member2.setName("member1");
    
    assertThrows(IllegalStateException.class, () -> {
      memberService.join(member1);
      memberService.join(member2);
    });

  }
}
