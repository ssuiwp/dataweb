package com.swp.dataweb.entity.Response;

public enum Status {

    SUCCESS(0, "success"),
    FAILURE(-1, "failure"),

    INTERNAL_SERVER_FAILURE(-1, "service internal error ......."),
    USER_NAME_PWD_ERROR(-1, "incorrect password or user name ......."),

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
    USER_QUERY_EMPTY(-51, "user information empty ......"),
    USER_NAME_EMPTY(-52, "missing user name ......."),
    USER_PASSWORD_EMPTY(-53, "missing user password ......."),
//    USER_TO_PROJECT_EMPTY(-54, "missing user's project ......."),

    // multiItem
    MULTIITEM_EMPTY(-20, "multiItem information empty ......."),
    MULTIITEM_QUERY_EMPTY(-21, "multiItem query information empty ......."),
    MULTIITEM_NAME_EMPTY(-22, "missing multiItem name ......."),
    MULTIITEM_FORM_EMPTY(-23, "missing belonging form ......."),
    MULTIITEM_ILLEGAL(-24, "illegal multiItem id ......"),
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
