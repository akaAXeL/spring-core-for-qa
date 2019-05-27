package com.acme.banking.dbo.ooad.domain;

import org.springframework.stereotype.Component;

/**
 * Inheritance as Code Reuse
 */
@Component
public class CheckingAccount extends SavingAccount {
    private double overdraft;

    public CheckingAccount() {

    }

    public CheckingAccount(long id, double amount, double overdraft) {
        super(id, amount);
        this.overdraft = overdraft;
    }

    @Override
    protected boolean validate(double amount) {
        return super.validate(amount + overdraft);
    }
}
