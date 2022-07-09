package com.example.kas.dao;

import com.example.kas.model.dto.Account;
import com.example.kas.model.dto.AccountHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDAO {
    public List<Account> selectAccountList();

    public void insertAccount(Account account);

    public List<AccountHistory> selectAccountHistoryList();

    public void insertAccountHistory(Account account);
}
