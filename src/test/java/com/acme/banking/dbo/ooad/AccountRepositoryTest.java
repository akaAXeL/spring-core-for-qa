package com.acme.banking.dbo.ooad;

import com.acme.banking.dbo.ooad.dal.AccountRepository;
import com.acme.banking.dbo.ooad.domain.Account;
import com.acme.banking.dbo.ooad.domain.SavingAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.fest.assertions.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ContextConfiguration({"classpath:test-ooad-context.xml", "classpath:ooad-context.xml"})
public class AccountRepositoryTest {

    @Autowired AccountRepository accountRepository;

    @MockBean
    @Qualifier("1")
    Account acc1;

    @MockBean
    @Qualifier("2")
    Account acc2;

    @Test
    @DirtiesContext
    public void shouldReturnAccountById() {
        //region Given
        double amount = 22;
        accountRepository.put(new SavingAccount(0, amount));
        Account account = new SavingAccount(0, amount);
        assertThat(accountRepository.findById(1)).isEqualTo(account);
    }

    @Test
    public void shouldReturnAllAccounts() {
        //region Given
        accountRepository.put(acc1);
        accountRepository.put(acc2);
        assertThat(accountRepository.findAll().size()).isEqualTo(2);
    }
}
