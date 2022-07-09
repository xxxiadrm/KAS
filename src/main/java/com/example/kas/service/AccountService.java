package com.example.kas.service;

import com.example.kas.dao.AccountDAO;
import com.example.kas.dao.UserDAO;
import com.example.kas.model.dto.Account;
import com.example.kas.model.dto.AccountHistory;
import com.example.kas.model.dto.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Account> getAmountByUser(Users users) {
        List<Account> result = new ArrayList<>();
        List<Account> accounts = accountDAO.selectAmountByUser(users);
        Map<String, Integer> map = new HashMap<>();
        for (Account account : accounts) {
            int amount = "Y".equals(account.getAccountHistory().getDepowith()) ? account.getAccountHistory().getAmount() : account.getAccountHistory().getAmount() * -1;
            map.put(account.getId(), map.getOrDefault(account.getId(), 0) + amount);
        }

        for (String id : map.keySet()) {
            AccountHistory accountHistory = new AccountHistory();
            accountHistory.setAmount(map.get(id));
            Account account = new Account();
            account.setId(id);
            account.setAccountHistory(accountHistory);
            result.add(account);
        }

        return result;
    }
}
