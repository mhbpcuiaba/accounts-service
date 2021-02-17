package com.awsome.techfin.accounts.service.domain.entity;

import com.awsome.techfin.accounts.service.domain.enums.OperationType;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name= "TRANSACTIONS")
@Entity
public class Transaction {

    @Id
    @SequenceGenerator(name = "SQ_TRANSACTIONS", sequenceName = "SQ_TRANSACTIONS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TRANSACTIONS")
    private Long id;

    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @Column(name = "OPERATION_TYPE_ID")
    private Integer operationType;

    @NotNull
    @DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=7, fraction=2)
    private BigDecimal amount;

    @Column(name = "EVENT_DATE")
    private LocalDateTime eventDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public OperationType getOperationType() {
        return OperationType.get(operationType);
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType.getId();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }
}
