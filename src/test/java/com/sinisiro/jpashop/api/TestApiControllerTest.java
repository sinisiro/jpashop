package com.sinisiro.jpashop.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TestApiController.class)
public class TestApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 테스트_GET() throws Exception {

        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();

        info.add("name", "칩");
        info.add("id", "chip");

        mockMvc.perform(get("/blog")       // 1, 2
                .params(info))              // 3
                .andExpect(status().isOk())     // 4
                .andExpect(content().string("칩의 블로그입니다. chip"))
                .andDo(print());                // 5
    }


}