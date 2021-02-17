package com.awsome.techfin.accounts.service.domain.enums;

public enum OperationType {
    CASH(1, "COMPRA A VISTA"),
    INSTALLMENT(2, "COMPRA PARCELADA"),
    WITHDRAW(3, "SAQUE"),
    PAYMENT(4, "PAGAMENTO");

    private Integer id;
    private String description;


    OperationType(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public static OperationType get(Integer id){
        for (OperationType opType : OperationType.values()) {
            if(opType.id.equals(id)){
                return opType;
            }
        }
        throw new IllegalArgumentException("ID [" + id + "] inválido para " + OperationType.class.getSimpleName());
    }

}