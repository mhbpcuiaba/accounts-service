package com.awsome.techfin.accounts.service.application.factory;

import com.awsome.techfin.accounts.service.application.dto.NewAccountDTO;
import com.awsome.techfin.accounts.service.domain.entity.Account;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountFactory {


    public Account create(NewAccountDTO newAccount) {
        Account account = new Account();
        BeanUtils.copyProperties(newAccount, account);
        return account;
    }


}
