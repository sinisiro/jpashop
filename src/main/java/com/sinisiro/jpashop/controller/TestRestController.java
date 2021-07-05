package com.sinisiro.jpashop.controller;

import com.google.gson.Gson;
import com.sinisiro.jpashop.form.ReqForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
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
    //클라이언트->서버 application/x-www-form-urlencoded
    //RequestBody 없이 선언하면  request 객체에 데이터가 넣어짐.
    //ModelAttribute,RequestBody 로 받는 2가지 방법이 있음
    @PostMapping("/sendCLData")
//    @CrossOrigin(origins = "http://localhost:7080")
//    public Map sendCLData(@ModelAttribute ReqForm requestMap) {
     public ResponseEntity<?> sendCLData(@RequestBody MultiValueMap<String, String> requestMap) {

        log.info("sendData 호출 "+ requestMap);
        Map<String, Object> resultData = new HashMap<>();

        String reqVal = requestMap.get("val").get(0);


        Gson gson = new Gson();
        resultData.put("val",reqVal);
        resultData.put("result","성공");
        resultData.put("result_cd","0000");

        String rslt = gson.toJson(resultData);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Header", "Content-Type");
        responseHeaders.set("Access-Control-Allow-Origin", "*");

        return new ResponseEntity(rslt, responseHeaders, HttpStatus.OK);
    }

    //클라이언트->서버 RequestBody 방식
    // application/json
    @ResponseBody
    @PostMapping("/sendCLData2")
    @CrossOrigin(origins = "http://localhost:7080")
    public ResponseEntity<?> sendCLData2(@RequestBody Map requestMap) {

        log.info("sendData2 호출 ");
        Map<String, Object> resultData = new HashMap<>();
        String reqVal = (String) requestMap.get("val");
        log.info("reqVal:"+reqVal);

        Gson gson = new Gson();
        resultData.put("val",reqVal);
        resultData.put("result","성공2");
        resultData.put("result_cd","0000");

        String rslt = gson.toJson(resultData);
        log.info("여기!!"+rslt);

        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set("Access-Control-Allow-Header", "Content-Type");
//        responseHeaders.set("Access-Control-Allow-Origin", "*");

        return new ResponseEntity(rslt, responseHeaders, HttpStatus.OK);

    }

}
