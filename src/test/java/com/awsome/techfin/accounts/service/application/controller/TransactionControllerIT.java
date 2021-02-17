package com.awsome.techfin.accounts.service.application.controller;

import br.com.six2six.fixturefactory.Fixture;
import com.awsome.techfin.accounts.service.application.dto.CreatedAccountDTO;
import com.awsome.techfin.accounts.service.application.dto.NewAccountDTO;
import com.awsome.techfin.accounts.service.application.dto.NewTransactionDTO;
import com.awsome.techfin.accounts.service.application.dto.RegisteredTransactionDTO;
import com.awsome.techfin.accounts.service.application.exception.AccountsServiceResponseError;
import com.awsome.techfin.accounts.service.domain.repository.AccountRepository;
import com.awsome.techfin.accounts.service.domain.repository.TransactionRepository;
import com.awsome.techfin.accounts.service.support.AbstractIT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TransactionControllerIT extends AbstractIT {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Test
    public void createTransactionSuccess() {
        NewAccountDTO validNewAccount = Fixture.from(NewAccountDTO.class).gimme("valid");
        HttpEntity<NewAccountDTO> entity = new HttpEntity<>(validNewAccount);

        ResponseEntity<CreatedAccountDTO> response = restTemplate.exchange("/accounts", HttpMethod.POST, entity, CreatedAccountDTO.class);
        CreatedAccountDTO createdAccount = response.getBody();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(createdAccount.getId());

        NewTransactionDTO validTransactionDTO = Fixture.from(NewTransactionDTO.class).gimme("valid");
        validTransactionDTO.setAccountId(createdAccount.getId());
        HttpEntity<NewTransactionDTO> entityTransaction = new HttpEntity<>(validTransactionDTO);
        ResponseEntity<RegisteredTransactionDTO> responseTransactionSuccess = restTemplate.exchange("/transactions", HttpMethod.POST, entityTransaction, RegisteredTransactionDTO.class);
        Assertions.assertEquals(HttpStatus.OK, responseTransactionSuccess.getStatusCode());
        RegisteredTransactionDTO registeredTransactionDTO = responseTransactionSuccess.getBody();
        Assertions.assertNotNull(registeredTransactionDTO.getIdTransaction());
        transactionRepository.deleteById(registeredTransactionDTO.getIdTransaction());
        accountRepository.deleteById(createdAccount.getId());
    }

    @Test
    public void createTransactionFailForUnknownAccountId() {
        NewTransactionDTO validTransactionDTO = Fixture.from(NewTransactionDTO.class).gimme("valid");
        HttpEntity<NewTransactionDTO> entity = new HttpEntity<>(validTransactionDTO);
        ResponseEntity<AccountsServiceResponseError> responseFail = restTemplate.exchange("/transactions", HttpMethod.POST, entity, AccountsServiceResponseError.class);
        AccountsServiceResponseError responseError = responseFail.getBody();
        Assertions.assertEquals(HttpStatus.CONFLICT, responseFail.getStatusCode());
        Assertions.assertEquals("breaks fk_to_account", responseError.getMessage());
    }

    @Test
    public void createtransactionFailInvalidRequest() {
        HttpEntity<NewTransactionDTO> entity = new HttpEntity<>(new NewTransactionDTO());
        ResponseEntity<AccountsServiceResponseError> responseFail = restTemplate.exchange("/transactions", HttpMethod.POST, entity, AccountsServiceResponseError.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseFail.getStatusCode());
    }
}
