package com.awsome.techfin.accounts.service.application.dto;

import br.com.six2six.fixturefactory.Fixture;
import com.awsome.techfin.accounts.service.support.AbstractValidationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class NewAccountDTOValidationTest extends AbstractValidationTest {

    @Test
    public void invalidNewAccountDTOMustFail() {
        NewAccountDTO emptyNewAccount = Fixture.from(NewAccountDTO.class).gimme("empty");
        Set<ConstraintViolation<NewAccountDTO>> validate = validator.validate(emptyNewAccount);
        Assertions.assertEquals(3, validate.size());
    }

    @Test
    public void validNewAccountDTOMustSuccess() {
        NewAccountDTO validNewAccount = Fixture.from(NewAccountDTO.class).gimme("valid");
        Set<ConstraintViolation<NewAccountDTO>> validate = validator.validate(validNewAccount);
        Assertions.assertEquals(0, validate.size());
    }
}
