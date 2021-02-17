
CREATE TABLE ACCOUNT
(
    id                     BIGINT NOT NULL   CONSTRAINT account_pkey PRIMARY KEY ,
    document_number        VARCHAR(11) UNIQUE NOT NULL,
    email                  VARCHAR(50) UNIQUE NOT NULL,
    mobile                 VARCHAR(11) UNIQUE NOT NULL
--         status                  CHAR(1)         CHECK (status IN ('A','I'))     NOT NULL
);

CREATE SEQUENCE SQ_ACCOUNT START WITH 1 INCREMENT BY 1 ;

-- CREATE INDEX filial_codigo ON public.filial ( codigo );
-- CREATE INDEX filial_cnpj ON public.filial ( cnpj );
-- CREATE INDEX filial_cnae ON public.filial ( cnae );
-- CREATE INDEX filial_created_at ON public.filial ( created_at );
-- CREATE INDEX filial_ativo ON public.filial ( ativo );
-- CREATE INDEX filial_updated_at ON public.filial ( updated_at );
