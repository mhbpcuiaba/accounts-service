package com.awsome.techfin.accounts.service.application.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NewAccountDTO {

    @NotNull
    @Size(min = 11, max = 11, message = "Document number must be 11 characters")
    private String documentNumber;

    @NotNull
    @Size(min = 11, max = 11, message = "Mobile must be 11 characters")
    private String mobile;

    @NotNull
    @Email(message = "Email should be valid")
    private String email;

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
