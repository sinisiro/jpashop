package com.sinisiro.jpashop.service;

import com.sinisiro.jpashop.domain.Member;
import com.sinisiro.jpashop.repository.MemberRepository;
import com.sinisiro.jpashop.repository.MemberRepositoryJpaData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
//@AllArgsConstructor
public class MemberService {
    
    @Autowired
    private final MemberRepository memberRepository;

    //21.08 Spring Data로 처리
    private final MemberRepositoryJpaData memberRepositoryJpaData;

//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        
//        memberRepository.save(member);
        memberRepositoryJpaData.save(member);
        return member.getId();
        
    }

    private void validateDuplicateMember(Member member) {
//        List<Member> findMembers = memberRepository.findByName(member.getName());
        List<Member> findMembers = memberRepositoryJpaData.findByName(member.getName());
        
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     *
     * 회원정보 수정
     */
    @Transactional
    public void update(Long id, String name)
    {
//        Member member = memberRepository.findOne(id);
        Member member = memberRepositoryJpaData.findById(id).get();
        member.setName(name);
    }


    /**
     * 전체회원 조회
     * 
     */
    public List<Member> findMembers(){
//        return memberRepository.findAll();
        return (List<Member>) memberRepositoryJpaData.findAll();
    }

    /**
     * 전체회원 조회
     *
     */
    public Member findOne(Long memberId){
//        return memberRepository.findOne(memberId);
        return memberRepositoryJpaData.findById(memberId).get();
    }
    


}
