package com.example.kas.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountHistory {
    private String depowith;
    private int amount;
    private LocalDateTime depositDate;
}
