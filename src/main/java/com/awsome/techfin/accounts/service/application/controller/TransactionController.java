package com.awsome.techfin.accounts.service.application.controller;

import com.awsome.techfin.accounts.service.application.dto.NewTransactionDTO;
import com.awsome.techfin.accounts.service.application.dto.RegisteredTransactionDTO;
import com.awsome.techfin.accounts.service.application.facade.TransactionFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionFacade transactionFacade;

    protected final Log logger = LogFactory.getLog(this.getClass());

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid NewTransactionDTO newAccountDTO) {
        RegisteredTransactionDTO registerTransaction = transactionFacade.register(newAccountDTO);
        logger.info("Transaction[" + registerTransaction.getIdTransaction() + "] has been registerd for account[" + newAccountDTO.getAmount() + "]");
        return new ResponseEntity(registerTransaction, HttpStatus.OK);
    }

}
