package com.code.edu.dto;

import com.code.edu.model.EduPersiom;
import com.code.edu.model.EduSecurity;

import java.util.List;

public class EduSecurityDto extends EduSecurity {
    private List<EduPersiom> eduPersioms;

    public List<EduPersiom> getEduPersioms() {
        return eduPersioms;
    }

    public void setEduPersioms(List<EduPersiom> eduPersioms) {
        this.eduPersioms = eduPersioms;
    }
}