package com.awsome.techfin.accounts.service.application.dto;

import br.com.six2six.fixturefactory.Fixture;
import com.awsome.techfin.accounts.service.support.AbstractValidationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class NewTransactionDTOValidationTest extends AbstractValidationTest {

    @Test
    public void invalidTransactionDTOMustFail() {
        NewTransactionDTO emptyTransactionDTO = Fixture.from(NewTransactionDTO.class).gimme("empty");
        Set<ConstraintViolation<NewTransactionDTO>> validate = validator.validate(emptyTransactionDTO);
        Assertions.assertEquals(3, validate.size());
    }

    @Test
    public void unknownOperationTypeTransactionDTOMustFail() {
        NewTransactionDTO emptyTransactionDTO = Fixture.from(NewTransactionDTO.class).gimme("outOfRange");
        Set<ConstraintViolation<NewTransactionDTO>> validate = validator.validate(emptyTransactionDTO);
        Assertions.assertEquals(1, validate.size());
    }

    @Test
    public void validTransactionDTOMustSuccess() {
        NewTransactionDTO validTransactionDTO = Fixture.from(NewTransactionDTO.class).gimme("valid");
        Set<ConstraintViolation<NewTransactionDTO>> validate = validator.validate(validTransactionDTO);
        Assertions.assertEquals(0, validate.size());
    }
}
