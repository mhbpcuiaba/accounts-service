package com.awsome.techfin.accounts.service.application.facade;

import com.awsome.techfin.accounts.service.application.dto.NewTransactionDTO;
import com.awsome.techfin.accounts.service.application.dto.RegisteredTransactionDTO;
import com.awsome.techfin.accounts.service.application.factory.TransactionFactory;
import com.awsome.techfin.accounts.service.application.mapper.TransactionMapper;
import com.awsome.techfin.accounts.service.domain.entity.Transaction;
import com.awsome.techfin.accounts.service.domain.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionFacade {

    @Autowired
    private TransactionFactory transactionFactory;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Transactional
    public RegisteredTransactionDTO register(NewTransactionDTO newTransaction) {
        Transaction transaction = transactionFactory.create(newTransaction);
        Transaction resgisteredTransaction = transactionRepository.save(transaction);
        return transactionMapper.map(resgisteredTransaction);
    }
}
