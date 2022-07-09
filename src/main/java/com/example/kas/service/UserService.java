package com.example.kas.service;

import com.example.kas.dao.UserDAO;
import com.example.kas.model.dto.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public List<Users> getUserList() {
        return userDAO.selectUserList();
    }

    public void setUser(Users users) {
        userDAO.insertUser(users);
    }
}
