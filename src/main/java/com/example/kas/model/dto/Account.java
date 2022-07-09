package com.example.kas.model.dto;

import lombok.Data;

@Data
public class Account {
    private String id;
    private Users users;
    private AccountHistory accountHistory;
}
