package com.code.edu.model;

import java.util.Date;

public class JiajuLunbo extends BaseModel<Long> {
    private Date gmtCreated;

    private Date gmtModified;

    private Byte isDeleted;

    private String lunboUrl;

    private String title;

    private Long companyId;

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

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getLunboUrl() {
        return lunboUrl;
    }

    public void setLunboUrl(String lunboUrl) {
        this.lunboUrl = lunboUrl == null ? null : lunboUrl.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}