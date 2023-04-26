package com.example.json.resttemplate;

import org.springframework.http.ResponseEntity;
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
        studentRequestBody.setName("Tracy");

        Student result = restTemplate.postForObject(
                "https://mocki.io/v1/c6eaf0a7-aa7f-46d3-b018-90df052a60d1",
                studentRequestBody,
                Student.class
        );

        return "postForObject success";
    }
}
