package com.awsome.techfin.accounts.service.support;

import org.junit.jupiter.api.BeforeAll;

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;

public abstract class AbstractTest {

    @BeforeAll
    public static void setUpClass() {
        loadTemplates("com.awsome.techfin.accounts.service.fixture");
    }

}
