package com.awsome.techfin.accounts.service.application.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountsServiceResponseError implements Serializable {

    //TODO refacoe to be thread safe
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String code;
    private HttpStatus httpStatus;
    private String message;
    private String messageDetail;
    private String date;

    public AccountsServiceResponseError() {}

    private AccountsServiceResponseError(String code, HttpStatus httpStatus, String message, String messageDetail, String date) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
        this.messageDetail = messageDetail;
        this.date = date;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageDetail() {
        return messageDetail;
    }

    public String getDate() {
        return date;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessageDetail(String messageDetail) {
        this.messageDetail = messageDetail;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static final class Builder {
        private String code;
        private HttpStatus httpStatus;
        private String message;
        private String messageDetail;
        private String date;

        public Builder() {}

        public static Builder anAutorizadorErrorMapper() {
            return new Builder();
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder httpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            this.code = httpStatus.toString();
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder message(Exception e) {
            this.message = e.getMessage();
            return this;
        }

        public Builder messageDetail(String messageDetail) {
            this.messageDetail = messageDetail;
            return this;
        }

        public Builder date(Date date) {
            this.date = SDF.format(date);
            return this;
        }

        public AccountsServiceResponseError build() {
            return new AccountsServiceResponseError(this.code, this.httpStatus, this.message, this.messageDetail, this.date);
        }
    }
}
