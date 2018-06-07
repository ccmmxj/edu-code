package com.code.edu.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BaseModel<L> {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private L id;

    public L getId() {
        return id;
    }

    public void setId(L id) {
        this.id = id;
    }
}
