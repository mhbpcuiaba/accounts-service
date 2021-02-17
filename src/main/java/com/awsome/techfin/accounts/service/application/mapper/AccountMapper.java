package com.awsome.techfin.accounts.service.application.mapper;

import com.awsome.techfin.accounts.service.application.dto.CreatedAccountDTO;
import com.awsome.techfin.accounts.service.application.dto.ViewAccountDTO;
import com.awsome.techfin.accounts.service.domain.entity.Account;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public CreatedAccountDTO mapToCreatedAccountDTO(Account account) {
        CreatedAccountDTO createdAccountDTO = new CreatedAccountDTO();
        BeanUtils.copyProperties(account, createdAccountDTO);
        return createdAccountDTO;
    }

    public ViewAccountDTO mapToViewAccountDTO(Account account) {
        ViewAccountDTO viewAccountDTO = new ViewAccountDTO();
        BeanUtils.copyProperties(account, viewAccountDTO);
        viewAccountDTO.setAccountId(account.getId());
        return viewAccountDTO;
    }
}
