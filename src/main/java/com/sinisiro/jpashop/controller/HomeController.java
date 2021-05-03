package com.sinisiro.jpashop.controller;

import com.sinisiro.jpashop.domain.Test;
import com.sinisiro.jpashop.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@Transactional
public class HomeController {

    private final TestRepository testRepository;

    @RequestMapping("/")
    public String home(){
        log.info("home contorller");
        Test item = new Test();
        item = testRepository.findOne(3L);

//        em.remove(entity); 처럼 바로 지우지 마시고
//
//        Entity entity = em.find(조건); 으로 삭제하실 데이터를 한번 검색하여 영속성 컨텍스트에 저장하고
//
//        em.remove(entity); 이렇게 지워주시면 될것 같습니다.

        testRepository.delete(item);

        return "home";
    }
}
