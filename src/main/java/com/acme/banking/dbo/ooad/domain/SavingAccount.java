package com.acme.banking.dbo.ooad.domain;

import org.springframework.stereotype.Component;

/**
 * Encapsulated state + behavior
 * POJO
 */
@Component
public class SavingAccount implements Account {
    private long id;
    private double amount;

    public SavingAccount() {

    }

    public SavingAccount(long id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    //Contract

    @Override
    public long getId() {
        return id;
    }
    @Override
    public double getAmount() {
        return amount;
    }

    /**
     * TM
     */
    @Override
    public void withdraw(double amount) {
        if (validate(amount)) throw new IllegalStateException();
        this.amount -= amount;
    }

    @Override
    public void deposit(double amount) {
        this.amount += amount;
    }

    protected boolean validate(double amount) {
        return amount > this.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SavingAccount that = (SavingAccount) o;

        if (id != that.id) return false;
        return Double.compare(that.amount, amount) == 0;
    }
}
