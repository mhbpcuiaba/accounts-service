package com.awsome.techfin.accounts.service.application.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RegisteredTransactionDTO {

    private Long idTransaction;

    public RegisteredTransactionDTO(){}

    public RegisteredTransactionDTO(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }
}
