package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * jpabook.jpashop.api MemberApiController
 *
 * @author : K
 */
@RestController
@RequiredArgsConstructor
public class MemberApiController {

  private final MemberService memberService;
  
  // 엔티티 직접 반환 X
  @GetMapping("/api/v1/members")
  public List<Member> membersV1(){
    return memberService.findMembers();
  }
  @GetMapping("/api/v2/members")
  public Result memberV2(){
    List<MemberDto> collect = memberService.findMembers().stream()
            .map(m -> new MemberDto(m.getName()))
            .collect(Collectors.toList());
    
    return new Result(collect);
  }
  

  // 기존 Entity로 Param 처리, 외부 entity 노출x
  @PostMapping("/api/v1/members")
  public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
    Long id = memberService.join(member);
    return new CreateMemberResponse(id);
  }

  // 별도 DTO를 만들어 Param 처리 API는 등록 이랑 응답 항상 DTO로
  @PostMapping("/api/v2/members")
  public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
    Member member = new Member();
    member.setName(request.getName());
    Long id = memberService.join(member);
    return new CreateMemberResponse(id);
  }

  // update
  @PutMapping("/api/v2/members/{id}")
  public UpdateMemberResponse updateMemberV2(
      @PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest request) {
    memberService.update(id, request.getName());
    Member findMember = memberService.findOne(id);
    return new UpdateMemberResponse(findMember.getId(), findMember.getName());
  }

  @Data
  static class CreateMemberResponse {
    private Long id;

    public CreateMemberResponse(Long id) {
      this.id = id;
    }
  }

  @Data
  static class CreateMemberRequest {
    private String name;
  }

  @Data
  static class UpdateMemberRequest {
    private String name;
  }

  @Data
  @AllArgsConstructor
  static class UpdateMemberResponse {
    private Long id;
    private String name;
  }
  
  @Data
  @AllArgsConstructor
  static class Result<T>{
    private T data;
}

@Data
@AllArgsConstructor
static class MemberDto{
    private String name;
}
  
}
