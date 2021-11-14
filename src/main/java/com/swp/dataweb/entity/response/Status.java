package com.swp.dataweb.entity.response;

public enum Status {

    SUCCESS(0, "success"),
    FAILURE(-1, "failure"),

    INTERNAL_SERVER_FAILURE(-1, "service internal error ......."),
    USER_NAME_PWD_ERROR(-1, "incorrect password or user name ......."),

    //validatedError 属性校验错误
    VALIDATED_ERROR(-40,"field empty"),

    // subject
    SUBJECT_EMPTY(-10, "subject information empty ......."),
    SUBJECT_QUERY_EMPTY(-11, "subject query information empty ......."),
    SUBJECT_NAME_EMPTY(-12, "missing subject name ......."),
    SUBJECT_UNIT_EMPTY(13, "missing subject unit ......."),

    // form
    FORM_EMPTY(-20, "form information empty ......."),
    FORM_QUERY_EMPTY(-21, "form query information empty ......."),
    FORM_NAME_EMPTY(-22, "missing form name ......."),
    FORM_SUBJECT_EMPTY(-23, "missing belonging subject ......."),
    FORM_ILLEGAL(-24, "illegal form id ......"),



    // User
    USER_EMPTY(-50, "user information empty ......"),
    USER_REGISTER_FAILED(-54, "user register failed ......"),
    USER_QUERY_EMPTY(-51, "user information empty ......"),
    USER_NAME_EMPTY(-52, "missing user name ......."),
    USER_PASSWORD_EMPTY(-53, "missing user password ......."),
//    USER_TO_PROJECT_EMPTY(-54, "missing user's project ......."),

    // multiItem
    ITEM_EMPTY(-30, "multiItem information empty ......."),
    ITEM_QUERY_EMPTY(-31, "multiItem query information empty ......."),
    ITEM_NAME_EMPTY(-32, "missing multiItem name ......."),
    ITEM_FORM_EMPTY(-33, "missing belonging form ......."),
    ITEM_ILLEGAL(-34, "illegal multiItem id ......"),
    ;


    private final int code;
    private final String desc;

    Status(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
