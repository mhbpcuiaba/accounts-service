package com.awsome.techfin.accounts.service.domain.entity;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @SequenceGenerator(name = "SQ_ACCOUNT", sequenceName = "SQ_ACCOUNT", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ACCOUNT")
    private Long id;

    @Column(name = "document_number")
    private String documentNumber;
    private String email;
    private String mobile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
