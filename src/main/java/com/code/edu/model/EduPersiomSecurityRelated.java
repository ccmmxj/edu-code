package com.code.edu.model;

import java.util.Date;

public class EduPersiomSecurityRelated extends BaseModel<Long>{
    private Long persiomId;

    private Long securityId;

    private Byte isDeleted;

    private Date gmtModified;

    private Date gmtCreated;

    public Long getPersiomId() {
        return persiomId;
    }

    public void setPersiomId(Long persiomId) {
        this.persiomId = persiomId;
    }

    public Long getSecurityId() {
        return securityId;
    }

    public void setSecurityId(Long securityId) {
        this.securityId = securityId;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
}