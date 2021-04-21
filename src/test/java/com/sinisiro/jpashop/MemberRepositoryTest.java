//package com.sinisiro.jpashop;
//
//import com.sinisiro.jpashop.domain.Member;
//import com.sinisiro.jpashop.domain.testMember;
//import com.sinisiro.jpashop.repository.MemberRepository;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.transaction.Transactional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class MemberRepositoryTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void testMember() {
//        testMember member = new testMember();
//
//        member.setUsername("memberA");
//        Long savedId = memberRepository.save(member);
//
//        member = memberRepository.find(savedId);
//        assertEquals(member.getUsername(), memberRepository.find(savedId).getUsername());
//    }
//}
//
