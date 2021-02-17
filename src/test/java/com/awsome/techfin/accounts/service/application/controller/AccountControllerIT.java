package com.awsome.techfin.accounts.service.application.controller;

import br.com.six2six.fixturefactory.Fixture;
import com.awsome.techfin.accounts.service.application.dto.CreatedAccountDTO;
import com.awsome.techfin.accounts.service.application.dto.NewAccountDTO;
import com.awsome.techfin.accounts.service.application.dto.ViewAccountDTO;
import com.awsome.techfin.accounts.service.application.exception.AccountsServiceResponseError;
import com.awsome.techfin.accounts.service.domain.repository.AccountRepository;
import com.awsome.techfin.accounts.service.support.AbstractIT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

public class AccountControllerIT extends AbstractIT {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void createAccountSuccess() {
        NewAccountDTO validNewAccount = Fixture.from(NewAccountDTO.class).gimme("valid");
        HttpEntity<NewAccountDTO> entity = new HttpEntity<>(validNewAccount);

        ResponseEntity<CreatedAccountDTO> response = restTemplate.exchange("/accounts", HttpMethod.POST, entity, CreatedAccountDTO.class);
        CreatedAccountDTO createdAccount = response.getBody();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(createdAccount.getId());
        Assertions.assertNotNull(createdAccount.getDocumentNumber());
        Assertions.assertNotNull(createdAccount.getEmail());
        Assertions.assertNotNull(createdAccount.getMobile());
        accountRepository.deleteById(createdAccount.getId());
    }

    @Test
    public void createAcctounWithDuplicateDataFail() {
        NewAccountDTO validNewAccount = Fixture.from(NewAccountDTO.class).gimme("valid");
        HttpEntity<NewAccountDTO> entity = new HttpEntity<>(validNewAccount);

        ResponseEntity<CreatedAccountDTO> response = restTemplate.exchange("/accounts", HttpMethod.POST, entity, CreatedAccountDTO.class);
        CreatedAccountDTO createdAccount = response.getBody();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(createdAccount.getId());
        Assertions.assertNotNull(createdAccount.getDocumentNumber());
        Assertions.assertNotNull(createdAccount.getEmail());
        Assertions.assertNotNull(createdAccount.getMobile());

        ResponseEntity<AccountsServiceResponseError> responseFail = restTemplate.exchange("/accounts", HttpMethod.POST, entity, AccountsServiceResponseError.class);
        AccountsServiceResponseError responseError = responseFail.getBody();
        Assertions.assertEquals(HttpStatus.CONFLICT, responseFail.getStatusCode());
        Assertions.assertEquals("breaks account_document_number_key", responseError.getMessage());
        accountRepository.deleteById(createdAccount.getId());
    }


    @Test
    public void createAccountFetchedWithSuccess() {
        NewAccountDTO validNewAccount = Fixture.from(NewAccountDTO.class).gimme("valid");
        HttpEntity<NewAccountDTO> entity = new HttpEntity<>(validNewAccount);

        ResponseEntity<CreatedAccountDTO> response = restTemplate.exchange("/accounts", HttpMethod.POST, entity, CreatedAccountDTO.class);
        CreatedAccountDTO createdAccount = response.getBody();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(createdAccount.getId());
        Assertions.assertNotNull(createdAccount.getDocumentNumber());
        Assertions.assertNotNull(createdAccount.getEmail());
        Assertions.assertNotNull(createdAccount.getMobile());

        ResponseEntity<ViewAccountDTO> responseFetch = restTemplate.exchange("/accounts/" + createdAccount.getId(), HttpMethod.GET, entity, ViewAccountDTO.class);
        ViewAccountDTO body = responseFetch.getBody();
        Assertions.assertNotNull(body.getAccountId());
        Assertions.assertNotNull(body.getDocumentNumber());
        Assertions.assertEquals(HttpStatus.OK, responseFetch.getStatusCode());
        accountRepository.deleteById(createdAccount.getId());
    }

    @Test
    public void fetchAccountWithNonExistAccountFail() {
        ResponseEntity<AccountsServiceResponseError> responseError = restTemplate.getForEntity("/accounts/" + 1_000_000,  AccountsServiceResponseError.class);
        AccountsServiceResponseError error = responseError.getBody();
        Assertions.assertEquals("Could not find account with id = 1000000", error.getMessage());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseError.getStatusCode());
    }

    @Test
    public void createAccountFailInvalidRequest() {
        HttpEntity<NewAccountDTO> entity = new HttpEntity<>(new NewAccountDTO());
        ResponseEntity<AccountsServiceResponseError> responseFail = restTemplate.exchange("/accounts", HttpMethod.POST, entity, AccountsServiceResponseError.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseFail.getStatusCode());
    }

}
