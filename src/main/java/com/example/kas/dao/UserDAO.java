package com.example.kas.dao;

import com.example.kas.model.dto.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO {
    public List<Users> selectUserList();

    public void insertUser(Users users);
}
