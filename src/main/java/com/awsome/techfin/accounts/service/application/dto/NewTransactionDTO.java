package com.awsome.techfin.accounts.service.application.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NewTransactionDTO {

    @NotNull
    private Long accountId;

    @Min(value = 1)
    @Max(value = 4)
    @NotNull
    private Integer operationTypeId;

    @NotNull
    @DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=7, fraction=2)
    private BigDecimal amount;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getOperationTypeId() {
        return operationTypeId;
    }

    public void setOperationTypeId(Integer operationTypeId) {
        this.operationTypeId = operationTypeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
