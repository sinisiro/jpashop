package com.sinisiro.jpashop.api;


import com.sinisiro.jpashop.domain.Member;
import com.sinisiro.jpashop.service.MemberService;
import com.sun.istack.NotNull;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Api(tags = { "1. MemberApiController" }, description = "사용자 정보") // Swagger 최상단 Controller 명칭
public class MemberApiController {

    private final MemberService memberService;

    @ApiOperation(value = "사용자 정보 저장(엔티티)", notes = "request name 사용자 이름 저장.")
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }


    /**
     * 등록 V2: 요청 값으로 Member 엔티티 대신에 별도의 DTO를 받는다.
     */
    @ApiOperation(value = "사용자 정보 저장(DTO)", notes = "request name 사용자 이름 갱신.")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "name", value="이름", required=true, dataType = "json", paramType="body")
//    })
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(
            @RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @Data
    static class CreateMemberRequest {
        @ApiModelProperty(value="이름", example = "맹수")
        private String name;
    }

    @Data
    class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;


        }
    }


    /**
     * 수정 API
     * Valid : parameter 내 validation check (@notnull 같은)
     */
    @ApiOperation(value = "사용자 정보 갱신", notes = "id param,  request 사용자 이름 갱신.")
    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }
    @Data
    static class UpdateMemberRequest {
        @NotNull
        private String name;
    }
    @Data
    @AllArgsConstructor
    class UpdateMemberResponse {
        private Long id;
        private String name;
    }


    /**
     * 조회 V2: 응답 값으로 엔티티가 아닌 별도의 DTO를 반환한다.
     * [
     *
     */
    @ApiOperation(value = "사용자 정보 조회", notes = "Member 테이블 사용자 이름 조회.")
    @GetMapping("/api/v2/members")
    public Result membersV2() {
        List<Member> findMembers = memberService.findMembers();//엔티티 -> DTO 변환
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());
//        return collect;
        return new Result(collect.size(), collect);
    }
    @Data
    @AllArgsConstructor
    class Result<T> {
        private int size;
        private T data;
    }

    @Data
    @AllArgsConstructor
    class MemberDto {
        private String name;
    }



}
