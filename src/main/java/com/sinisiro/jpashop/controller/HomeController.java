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
        // jpa 삭제 연습
//        Test item = new Test();
//        item = testRepository.findOne(3L);
//        testRepository.delete(item);

        return "home";
    }
}
