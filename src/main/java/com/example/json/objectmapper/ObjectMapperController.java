package com.example.json.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObjectMapperController {
    @GetMapping("/convert")
    public String convert() throws JsonProcessingException {
        User user = new User();
        user.setId(1);
        user.setName("Tracy");
        user.setContactEmail("test.com");

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonResult = objectMapper.writeValueAsString(user);
        // 將 user 轉換成 json 字串
        System.out.println("json 的值為： " + jsonResult);

        String json = "{" +
                "\"id\":1," +
                "\"name\":\"Tracy\"," +
                "\"age\":20," +
                "\"contact_email\":\"hello@test.com\"}";

        User userResult = objectMapper.readValue(json, User.class);
        // 將 json 字串轉換成 user object

        System.out.println("User 的 id 值為： " + userResult.getId());
        System.out.println("User 的 name 值為：" + userResult.getName());
        System.out.println("User 的 email 值為：" + userResult.getContactEmail());

        return "convert success";
    }
}
