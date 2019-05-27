package com.acme.banking.dbo.ooad.service;

import com.acme.banking.dbo.ooad.domain.Account;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    public void transfer(Account from, Account to, double amount) {
        from.withdraw(amount);
        to.deposit(amount);
    }
}
