package com.acme.banking.dbo.ooad.domain;


import org.springframework.stereotype.Component;

@Component
public interface Account {
    long getId();
    double getAmount();
    void withdraw(double amount);
    void deposit(double amount);
}
