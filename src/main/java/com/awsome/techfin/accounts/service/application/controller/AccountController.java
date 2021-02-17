package com.awsome.techfin.accounts.service.application.controller;

import com.awsome.techfin.accounts.service.application.dto.CreatedAccountDTO;
import com.awsome.techfin.accounts.service.application.dto.NewAccountDTO;
import com.awsome.techfin.accounts.service.application.dto.ViewAccountDTO;
import com.awsome.techfin.accounts.service.application.facade.AccountFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountFacade accountServiceFacade;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid NewAccountDTO newAccountDTO) {
        CreatedAccountDTO createdAccountDTO = accountServiceFacade.create(newAccountDTO);
        return new ResponseEntity(createdAccountDTO, HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity fetch(@PathVariable("accountId") Long accountId) {
        ViewAccountDTO viewAccountDTO = accountServiceFacade.fetchViewAccount(accountId);
        return new ResponseEntity(viewAccountDTO, HttpStatus.OK);
    }
}
