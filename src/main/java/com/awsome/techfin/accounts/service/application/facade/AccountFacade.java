package com.awsome.techfin.accounts.service.application.facade;

import com.awsome.techfin.accounts.service.application.dto.CreatedAccountDTO;
import com.awsome.techfin.accounts.service.application.dto.NewAccountDTO;
import com.awsome.techfin.accounts.service.application.dto.ViewAccountDTO;
import com.awsome.techfin.accounts.service.application.exception.AccountNotFoundException;
import com.awsome.techfin.accounts.service.application.factory.AccountFactory;
import com.awsome.techfin.accounts.service.application.mapper.AccountMapper;
import com.awsome.techfin.accounts.service.domain.entity.Account;
import com.awsome.techfin.accounts.service.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountFacade {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountFactory accountFactory;

    @Autowired
    private AccountMapper accountMapper;

    @Transactional
    public CreatedAccountDTO create(NewAccountDTO newAccount) {
        Account account = accountFactory.create(newAccount);
        Account accountSaved = accountRepository.save(account);
        return accountMapper.mapToCreatedAccountDTO(accountSaved);
    }

    public ViewAccountDTO fetchViewAccount(Long accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        Account account = accountOptional.orElseThrow(() -> new AccountNotFoundException("Could not find account with id = " + accountId));
        return accountMapper.mapToViewAccountDTO(account);
    }
}
