package com.awsome.techfin.accounts.service.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.awsome.techfin.accounts.service.application.dto.NewAccountDTO;

public class NewAccountDTOFixture implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(NewAccountDTO.class).addTemplate("valid", new Rule() {{
            add("documentNumber", "12345678999");
            add("mobile", random(String.class,"11985733131", "11985738765"));
            add("email", random(String.class,"marcoscba@gmail.com", "marcos_cuiaba@hotmail.com"));
        }}).addTemplate("empty", new Rule() {{
        }});;

    }
}
