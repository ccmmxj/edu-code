package com.code.edu.model;

import java.util.Date;

public class EduRolePersiomRelated extends BaseModel<Long> {

    private Long roleId;

    private Long persiomId;

    private Byte isDeleted;

    private Date gmtModified;

    private Date gmtCreated;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPersiomId() {
        return persiomId;
    }

    public void setPersiomId(Long persiomId) {
        this.persiomId = persiomId;
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