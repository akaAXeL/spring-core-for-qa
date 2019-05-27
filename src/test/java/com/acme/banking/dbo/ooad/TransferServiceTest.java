package com.acme.banking.dbo.ooad;

import com.acme.banking.dbo.ooad.domain.Account;
import com.acme.banking.dbo.ooad.service.TransferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ContextConfiguration({"classpath:test-ooad-context.xml", "classpath:ooad-context.xml"})
public class TransferServiceTest {

    @MockBean
    @Qualifier("ASD")
    Account fromAccount;

    @MockBean
    @Qualifier("DSA")
    Account toAccount;

    @Test
    public void shouldUpdateAccountsStateWhenTransfer() {
        //region Given
        TransferService sut = new TransferService();
        //endregion

        //region When
        sut.transfer(fromAccount, toAccount, 100.);
        //endregion

        //region Then
        verify(fromAccount, times(1)).withdraw(100.);
        verify(toAccount).deposit(anyDouble());
        //endregion
    }
}
