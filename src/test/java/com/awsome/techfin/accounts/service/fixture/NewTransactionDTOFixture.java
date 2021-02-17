package com.awsome.techfin.accounts.service.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.awsome.techfin.accounts.service.application.dto.NewTransactionDTO;

import java.math.BigDecimal;

public class NewTransactionDTOFixture implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(NewTransactionDTO.class).addTemplate("valid", new Rule() {{
            add("accountId", random(Long.class, range(1L, 200L)));
            add("operationTypeId", random(Integer.class, range(1L, 4L)));
            add("amount", new BigDecimal(100L));
        }}).addTemplate("outOfRange", new Rule() {{
            add("accountId", random(Long.class, range(1L, 200L)));
            add("operationTypeId",  random(Integer.class, range(10L, 40L)));
            add("amount", new BigDecimal(100L));

        }}).addTemplate("empty", new Rule() {{

        }});;

    }
}
