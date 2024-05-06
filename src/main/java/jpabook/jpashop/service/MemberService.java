package jpabook.jpashop.service;

import java.util.List;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * jpabook.jpashop.service MemberService
 *
 * @author : K
 */
@Service
@Transactional(readOnly = true) // 조회는 readonly 사용해서 더티체킹, 리소스 등 이점이 있음
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;

  /*
   * 회원 가입
   */
  @Transactional
  public Long join(Member member) {
    validateDuplicateMember(member);
    memberRepository.save(member);
    return member.getId();
  }

  /*
   * 중복 검사
   */
  private void validateDuplicateMember(Member member) {
    List<Member> findMembers = memberRepository.findByname(member.getName());
    if (!findMembers.isEmpty()) {
      throw new IllegalStateException("이미 존재하는 회원입니다.");
    }
  }

  /*
   * 전체 멤버 조회
   */
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  /*
   * 멤버 조회 id
   */
  public Member findOne(Long memberId) {
    return memberRepository.findOne(memberId);
  }
  
  /*
   * 멤버 업데이트
   */
  /* 커맨드와 쿼리를 분리한다*/
  @Transactional
  public void update(Long id, String name) {
    Member member = memberRepository.findOne(id);
    member.setName(name);
  }
}
