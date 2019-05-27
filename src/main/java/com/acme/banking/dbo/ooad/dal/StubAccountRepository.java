package com.acme.banking.dbo.ooad.dal;

import com.acme.banking.dbo.ooad.domain.Account;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository("Stub")
@Profile("test")
public class StubAccountRepository implements AccountRepository {
    private Map<Long, Account> accounts = new HashMap<>();

    public StubAccountRepository() {

    }

    public Account findById(long id) {
        return accounts.get(id);
    }

    public void put(Account account) {
        Long maxId = accounts.keySet().stream()
                .max(Comparator.naturalOrder())
                .orElse(0L);

        long newId = maxId + 1;
        accounts.put(newId, account);
    }

    public List<Account> findAll() {
        return new ArrayList(accounts.values());
    }
}

