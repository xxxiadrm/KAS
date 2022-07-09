package com.example.kas.controller;
import com.example.kas.exception.HttpStatusException;
import com.example.kas.model.dto.Users;
import com.example.kas.service.UserService;
import com.example.result.KasStatus;
import com.example.result.ResultMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultMessage> getUser() {
        List<Users> users = userService.getUserList();
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setStatus(KasStatus.SUCCESS.getCode());
        resultMessage.setDesc(KasStatus.SUCCESS.getMessage());
        resultMessage.setResult(users);
        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }

    @PostMapping(value = "v1/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultMessage> setUser(@RequestBody String request) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(request);

            // 필수 값 누락
            if ((!node.has("name")
                    || (!node.has("age")))) {
                throw new HttpStatusException(HttpStatus.BAD_REQUEST, KasStatus.PRI2002);
            }

            Users users = new Users();
            users.setName(node.get("name").asText());
            users.setAge(node.get("age").asInt());
            userService.setUser(users);
        } catch (JsonMappingException e) {
            throw new HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, KasStatus.PRE2002);
        } catch (JsonProcessingException e) {
            throw new HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, KasStatus.PRE2003);
        }

        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setStatus(KasStatus.SUCCESS.getCode());
        resultMessage.setDesc(KasStatus.SUCCESS.getMessage());
        return new ResponseEntity<>(resultMessage, HttpStatus.CREATED);
    }
}
