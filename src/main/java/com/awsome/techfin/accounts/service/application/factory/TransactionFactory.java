package com.awsome.techfin.accounts.service.application.factory;

import com.awsome.techfin.accounts.service.application.dto.NewTransactionDTO;
import com.awsome.techfin.accounts.service.domain.entity.Transaction;
import com.awsome.techfin.accounts.service.domain.enums.OperationType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionFactory {

    public Transaction create(NewTransactionDTO newTransaction) {
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(newTransaction, transaction);
        transaction.setOperationType(OperationType.get(newTransaction.getOperationTypeId()));
        transaction.setEventDate(LocalDateTime.now());
        return transaction;
    }
}
