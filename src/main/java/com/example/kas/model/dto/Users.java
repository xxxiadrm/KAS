package com.example.kas.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Users {
    private int id;
    private String name;
    private int age;
    private LocalDateTime registerDate;
}
