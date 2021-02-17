package com.awsome.techfin.accounts.service.application.facade;

import com.awsome.techfin.accounts.service.application.dto.ViewAccountDTO;
import com.awsome.techfin.accounts.service.application.exception.AccountNotFoundException;
import com.awsome.techfin.accounts.service.application.factory.AccountFactory;
import com.awsome.techfin.accounts.service.application.mapper.AccountMapper;
import com.awsome.techfin.accounts.service.domain.entity.Account;
import com.awsome.techfin.accounts.service.domain.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
public class AccountFacadeTest {

    @InjectMocks
    AccountFacade accountFacade;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountFactory accountFactory;

    @Mock
    private AccountMapper accountMapper;

    @Test
    public void fetchViewAccountWithSuccess() {
        Account account = new Account();
        Mockito.when(accountRepository.findById(any(Long.class))).thenReturn(Optional.of(account));
        Mockito.when(accountMapper.mapToViewAccountDTO(any(Account.class))).thenReturn(new ViewAccountDTO());
        ViewAccountDTO viewAccountDTO = accountFacade.fetchViewAccount(10L);
        Assertions.assertNotNull(viewAccountDTO);
    }

    @Test
    public void fetchViewAccountWithFailNotFound() {
        Assertions.assertThrows(AccountNotFoundException.class, () ->{
            ViewAccountDTO viewAccountDTO = accountFacade.fetchViewAccount(10L);
            Assertions.assertNotNull(viewAccountDTO);
        });
    }
}
