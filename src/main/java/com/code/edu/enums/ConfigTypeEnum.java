package com.code.edu.enums;

public enum ConfigTypeEnum {
    CARD_TYPE("card","卡片类型");
    private final String type;
    private final String desc;
    ConfigTypeEnum(String type,String desc){
        this.type = type;
        this.desc = desc;
    }

    public String type() {
        return type;
    }

    public String desc() {
        return desc;
    }
}
