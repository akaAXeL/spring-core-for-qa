package com.acme.banking.dbo.ooad;

import com.acme.banking.dbo.ooad.dal.AccountRepository;
import com.acme.banking.dbo.ooad.domain.Account;
import com.acme.banking.dbo.ooad.service.ReportingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ContextConfiguration({"classpath:test-ooad-context.xml", "classpath:ooad-context.xml"})
public class ReportingServiceTest {
    @MockBean
    private AccountRepository accounts; //mock(AccountRepository.class);

    @MockBean
    @Qualifier("acc")
    private Account account;

    @MockBean
    @Qualifier("acc1")
    private Account account1;

    @Test(expected = EntityNotFoundException.class)
    public void shouldGetExceptionWhenNoAccountFound() {
        when(accounts.findById(anyLong()))
                .thenThrow(new EntityNotFoundException());

        ReportingService sut = new ReportingService(accounts);
        sut.reportForAccount(0L);
    }

    @Test
    public void shouldGetReportForAccountWhenAccountExists() {
        //region Given
        when(account.getAmount()).thenReturn(100.0);
        when(account.getId()).thenReturn(0L);
        when(accounts.findById(0L)).thenReturn(account);
        ReportingService sut = new ReportingService(accounts);
        //endregion

        //region When
        String actualReport = sut.reportForAccount(0L);
        //endregion

        //region Then
        assertThat(actualReport)
                .contains("## " + String.valueOf(0L))
                .contains("100.0");
        //endregion
    }

    @Test
    public void shouldSetAccounts() {
        //region Given
        when(account.getAmount()).thenReturn(100.0);
        when(account1.getAmount()).thenReturn(200.0);
        when(account.getId()).thenReturn(0L);
        when(account1.getId()).thenReturn(3L);
        AccountRepository stubRepo = mock(AccountRepository.class);
        AccountRepository stubRepo1 = mock(AccountRepository.class);
        when(stubRepo.findById(0L)).thenReturn(account);
        when(stubRepo1.findById(3L)).thenReturn(account1);
        ReportingService sut = new ReportingService(stubRepo1);
        //endregion

        //region When
        sut.setAccounts(stubRepo1);
        String actualReport = sut.reportForAccount(3L);
        //endregion

        //region Then
        assertThat(actualReport)
                .contains("## " + String.valueOf(3L))
                .contains("200.0");
        //endregion
    }
}
