package com.sinisiro.jpashop.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("rest/v1")
public class TestRestController {

    //서버->서버(8080)
    @PostMapping("/sendSVData")
    public Map sendSVData(@RequestBody Map requestMap,  Model model, HttpServletRequest req, HttpServletResponse res) {

        log.info("sendData 호출 ");

        Map<String, Object> resultData = new HashMap<>();
        String reqVal = (String) requestMap.get("val");
        log.info("reqVal:"+reqVal);

        Gson gson = new Gson();
        resultData.put("val",reqVal);
        resultData.put("result","성공");
        resultData.put("result_cd","0000");

        String rslt = gson.toJson(resultData);
        log.info(rslt);

        return resultData;
    }
    //클라이언트->서버]
    //RequestBody 없이 선언하면  request 객체에 데이터가 넣어짐.
    @PostMapping("/sendCLData")
    @CrossOrigin(origins = "http://localhost:7080")
    public Map sendCLData(Model model, HttpServletRequest req, HttpServletResponse res) {

        log.info("sendData 호출 ");
        Map<String, Object> resultData = new HashMap<>();
        String reqVal =req.getParameter("val");
        log.info("reqVal:"+reqVal);

        Gson gson = new Gson();
        resultData.put("val",reqVal);
        resultData.put("result","성공");
        resultData.put("result_cd","0000");

        String rslt = gson.toJson(resultData);
//        res.setHeader("Access-Control-Allow-Header", "Content-Type");
//        res.setHeader("Access-Control-Allow-Origin", "*");

        log.info(rslt);

        return resultData;
    }
}
