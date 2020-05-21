package com.consoleapp1.enums;

public enum PropertyEnum {
    JDBC_CONNECTION_STRING("jdbc.connection.string"),
    INTITIAL_SQL_SCRIPT("initial.sql.script"),
    ;

    private final String key;

    PropertyEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
