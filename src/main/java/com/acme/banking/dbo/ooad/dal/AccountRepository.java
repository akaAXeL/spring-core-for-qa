package com.acme.banking.dbo.ooad.dal;

import com.acme.banking.dbo.ooad.domain.Account;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Profile("test")
public interface AccountRepository {
    Account findById(long id);
    void put(Account account);
    List<Account> findAll();
}
