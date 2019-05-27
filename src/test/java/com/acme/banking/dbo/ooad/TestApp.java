package com.acme.banking.dbo.ooad;

import com.acme.banking.dbo.ooad.dal.AccountRepository;
import com.acme.banking.dbo.ooad.domain.SavingAccount;
import com.acme.banking.dbo.ooad.service.ReportingService;
import com.acme.banking.dbo.ooad.service.TransferService;
import org.slf4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApp {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "test,prod"); //-Dspring.profiles.active="test,prod"

        try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("test-ooad-context.xml", "ooad-context.xml")) {
            //TODO Spring component acqusition styles: by type, by id, both
            Logger logger = context.getBean(Logger.class);

            AccountRepository accountRepository = context.getBean(AccountRepository.class);
            accountRepository.put(new SavingAccount(1, 100)); /** save() don't call validation till commit */
            accountRepository.put(new SavingAccount(2, 100)); /** save() don't call validation till commit */

            ReportingService reportingService = context.getBean(ReportingService.class);
            logger.info(">>>>> " + reportingService.reportForAccount(1L)); //TODO Refer project DDL and DML
            logger.info(">>>>> " + reportingService.reportForAccount(2L));

            context.getBean(TransferService.class).transfer(accountRepository.findById(1l), accountRepository.findById(2L), 100);
            logger.info(">>>>> " + reportingService.reportForAccount(1L));
            logger.info(">>>>> " + reportingService.reportForAccount(2L));

            accountRepository.findAll().forEach(account -> logger.info(">>>>> " + account));
            //TODO And now move to spring-context.xml and make action 'Show Diagram...'
        }
    }
}
