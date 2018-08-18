package com.code.edu.enums;

public enum AdvertEnum {
    RIGHT((byte)1,"右边"),
    BOTTOM((byte)0,"底部");
    private final byte code;
    private final String desc;
    AdvertEnum(byte code,String desc){
        this.code = code;
        this.desc = desc;
    }
}
