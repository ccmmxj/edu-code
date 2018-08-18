package com.code.edu.model;

import java.util.Date;

public class EduAdvert extends BaseModel<Long>{
    private String name;

    private Byte isDeleted;

    private Date gmtCreated;

    private Date gmtModified;

    private String advertUrl;

    private String toUrl;

    private Byte type;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getAdvertUrl() {
        return advertUrl;
    }

    public void setAdvertUrl(String advertUrl) {
        this.advertUrl = advertUrl == null ? null : advertUrl.trim();
    }

    public String getToUrl() {
        return toUrl;
    }

    public void setToUrl(String toUrl) {
        this.toUrl = toUrl == null ? null : toUrl.trim();
    }
}