package com.example.kas.controller;

import com.example.kas.exception.HttpStatusException;
import com.example.kas.model.dto.Account;
import com.example.kas.model.dto.AccountHistory;
import com.example.kas.model.dto.Users;
import com.example.kas.service.AccountService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.time.LocalDateTime.now;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping(value = "v1/account", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultMessage> getAccount() {
        List<Account> accounts = accountService.getAccountList();
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setStatus(KasStatus.SUCCESS.getCode());
        resultMessage.setDesc(KasStatus.SUCCESS.getMessage());
        resultMessage.setResult(accounts);
        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }

    @PostMapping(value = "v1/account", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultMessage> setAccount(@RequestBody String request) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(request);

            // 필수 값 누락
            if ((!node.has("id")
                    || (!node.has("accountId")))) {
                throw new HttpStatusException(HttpStatus.BAD_REQUEST, KasStatus.PRI2002);
            }
            Users users = new Users();
            users.setId(node.get("id").asInt());
            Account account = new Account();
            account.setId(node.get("accountId").asText());
            account.setUsers(users);
            accountService.setAccount(account);
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

    @GetMapping(value = "v1/account/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultMessage> getAccountHistory() {
        List<AccountHistory> accounts = accountService.getAccountHistoryList();
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setStatus(KasStatus.SUCCESS.getCode());
        resultMessage.setDesc(KasStatus.SUCCESS.getMessage());
        resultMessage.setResult(accounts);
        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }

    @PostMapping(value = "v1/account/history", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultMessage> setAccountHistory(@RequestBody String request) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(request);

            // 필수 값 누락
            if ((!node.has("accountId")
                    || (!node.has("depowith")) || (!node.has("amount")))) {
                throw new HttpStatusException(HttpStatus.BAD_REQUEST, KasStatus.PRI2002);
            }

            Account account = new Account();
            account.setId(node.get("accountId").asText());
            AccountHistory accountHistory = new AccountHistory();
            accountHistory.setDepowith(node.get("depowith").asText());
            accountHistory.setAmount(node.get("amount").asInt());
            account.setAccountHistory(accountHistory);
            accountService.setAccountHistory(account);
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

    @GetMapping(value = "v1/account/amount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultMessage> getAmountByUser(@RequestParam(value = "id") int id) {

        Users users = new Users();
        users.setId(id);
        List<Account> accounts = accountService.getAmountByUser(users);


        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setStatus(KasStatus.SUCCESS.getCode());
        resultMessage.setDesc(KasStatus.SUCCESS.getMessage());
        resultMessage.setResult(accounts);
        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }
}
