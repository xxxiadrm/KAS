package com.example.kas.service;

import com.example.kas.dao.AccountDAO;
import com.example.kas.dao.UserDAO;
import com.example.kas.model.dto.Account;
import com.example.kas.model.dto.AccountHistory;
import com.example.kas.model.dto.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountDAO accountDAO;

    public List<Account> getAccountList() {
        return accountDAO.selectAccountList();
    }

    public void setAccount(Account account) {
        accountDAO.insertAccount(account);
    }

    public List<AccountHistory> getAccountHistoryList() {
        return accountDAO.selectAccountHistoryList();
    }

    public void setAccountHistory(Account account) {
        accountDAO.insertAccountHistory(account);
    }
}
