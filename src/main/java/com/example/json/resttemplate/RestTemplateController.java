package com.example.json.resttemplate;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestTemplateController {
    @GetMapping("/getForObject")
    public String getForObject() {
        RestTemplate restTemplate = new RestTemplate();

        Student student = restTemplate.getForObject(
                "https://mocki.io/v1/c6eaf0a7-aa7f-46d3-b018-90df052a60d1",
                Student.class);

        System.out.println("Student 的 id 值為： " + student.getId());
        System.out.println("Student 的 name 值為： " + student.getName());
        System.out.println("Student 的 phone 值為： " + student.getContactPhone());

        return "getForObject success";
    }

    @GetMapping("/getForObjectWithParam")
    public String getForObjectWithParam() {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> queryParamMap = new HashMap<>();
        queryParamMap.put("graduate", true);
        // map 裡會放到時候使用 GET 方法請求時，在 url 後面所加的請求參數

        Student student = restTemplate.getForObject(
                "https://mocki.io/v1/c6eaf0a7-aa7f-46d3-b018-90df052a60d1",
                Student.class,
                queryParamMap);

        return "getForObject with param success";
    }

    @GetMapping("/getForEntity")
    public String getForEntity() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Student> studentEntity = restTemplate.getForEntity(
                "https://mocki.io/v1/c6eaf0a7-aa7f-46d3-b018-90df052a60d1",
                Student.class
        );
        // 取得整個 Http Response 回來

        System.out.println("http 狀態碼為：" + studentEntity.getStatusCode());

        Student student = studentEntity.getBody();
        // 取得 Http Response 的 Json 字串
        System.out.println("student 的 id 值為： " + student.getId());
        System.out.println("student 的 name 值為： " + student.getName());
        System.out.println("student 的 phone 值為： " + student.getContactPhone());

        return "getForEntity success";
    }

    @GetMapping ("/postForObject")
    public String postForObject() {
        RestTemplate restTemplate = new RestTemplate();

        Student studentRequestBody = new Student();
        studentRequestBody.setName("Judy");

        Student result = restTemplate.postForObject(
                "https://mocki.io/v1/5845c85e-ae41-4da9-b948-3ebc755f4c99",
                studentRequestBody,
                Student.class
        );

        return "postForObject success";
    }

    @GetMapping ("/postForEntity")
    public String postForEntity() {
        RestTemplate restTemplate = new RestTemplate();

        Student studentRequestBody = new Student();
        studentRequestBody.setName("Tracy");

        ResponseEntity<Student> responseEntity = restTemplate.postForEntity(
                "https://mocki.io/v1/5845c85e-ae41-4da9-b948-3ebc755f4c99",
                studentRequestBody,
                Student.class
        );
        // 取得整個 Http Response 回來

        return "postForEntity success";
    }

    @GetMapping("/exchange")
    public String exchange() {

        RestTemplate restTemplate = new RestTemplate();

        // 使用 exchange 發起 GET 請求
        // 定義請求過程中要帶上哪些 header
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("header1", "123");

        // 定義完後使用一個 HttpEntity 封裝起來
        HttpEntity requestEntity = new HttpEntity(requestHeaders);

        // 定義請求要帶哪些 parameter
        Map<String, Object> queryParamMap = new HashMap<>();
        queryParamMap.put("graduate", true);

        // 按照固定的順序將這些 parameter 傳進 exchange 方法裡
        ResponseEntity<Student> getStudentEntity = restTemplate.exchange(
                "https://mocki.io/v1/5845c85e-ae41-4da9-b948-3ebc755f4c99",
                HttpMethod.GET,
                requestEntity,
                Student.class,
                queryParamMap
        );

        // 使用 exchange 發起 POST 請求
        HttpHeaders requestHeaders2 = new HttpHeaders();
        requestHeaders2.set("header2", "456");
        requestHeaders2.setContentType(MediaType.APPLICATION_JSON);
        // 一定要記得加這個 Content-Type: application/json

        Student studentRequestBody = new Student();
        studentRequestBody.setName("John");

        HttpEntity requestEntity2 = new HttpEntity(studentRequestBody, requestHeaders2);

        ResponseEntity<Student> postStudentEntity = restTemplate.exchange(
                "https://mocki.io/v1/5845c85e-ae41-4da9-b948-3ebc755f4c99",
                HttpMethod.POST,
                requestEntity2,
                Student.class
        );
        return "exchange success";
    }
}
