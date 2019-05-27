package com.acme.banking.dbo.ooad;

import com.acme.banking.dbo.ooad.domain.Account;
import com.acme.banking.dbo.ooad.domain.CheckingAccount;
import com.acme.banking.dbo.ooad.domain.SavingAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ContextConfiguration({"classpath:test-ooad-context.xml", "classpath:ooad-context.xml"})
public class AccountTest {
    @Test(expected = IllegalStateException.class)
    public void shouldTrowExceptionWhenTryToWithdrawMore() {
        //region Given
        Account account = new CheckingAccount(0, 100, 50);
        account.withdraw(200);
    }

    @Test
    public void shouldIncreaseBalanceWhenDeposit() {
        //region Given
        Account account = new SavingAccount(0, 100);
        account.deposit(100);
        assertThat(account.getAmount()).isEqualTo(200);
    }

    @Test
    public void shouldDecreaseBalanceWhenWithdraw() {
        //region Given
        Account account = new SavingAccount(0, 100);
        account.withdraw(100);
        assertThat(account.getAmount()).isEqualTo(0);
    }

    @Test
    public void shouldReturnIdWhichEqualsToConstructor() {
        Account account = new SavingAccount(10, 1d);
        assertThat(account.getId()).isEqualTo(10L);
    }

    @Test
    public void shouldReturnFalseWhenCompareDifferentId() {
        Account account1 = new SavingAccount(9, 1d);
        Account account2 = new SavingAccount(10, 1d);
        assertThat(account1).isNotEqualTo(account2);
    }

    @Test
    public void shouldReturnFalseWhenCompareDifferentAmount() {
        Account account1 = new SavingAccount(10, 1d);
        Account account2 = new SavingAccount(10, 2d);
        assertThat(account1).isNotEqualTo(account2);
    }

    @Test
    public void shouldReturnFalseWhenCompareNullAccounts() {
        Account account1 = new CheckingAccount(10, 1d, 1d);
        Account account2 = new SavingAccount(10, 1d);
        assertThat(account1).isNotEqualTo(account2);
    }
}
