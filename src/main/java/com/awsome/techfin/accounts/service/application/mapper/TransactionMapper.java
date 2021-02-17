package com.awsome.techfin.accounts.service.application.mapper;

import com.awsome.techfin.accounts.service.application.dto.RegisteredTransactionDTO;
import com.awsome.techfin.accounts.service.domain.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public RegisteredTransactionDTO map(Transaction transaction) {
        return new RegisteredTransactionDTO(transaction.getId());
    }
}
