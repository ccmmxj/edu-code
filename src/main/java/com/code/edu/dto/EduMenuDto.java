package com.code.edu.dto;

import com.code.edu.model.EduMenu;

import java.util.List;

public class EduMenuDto extends EduMenu {
    private List<EduMenuDto> childEduMenu;

    public List<EduMenuDto> getChildEduMenu() {
        return childEduMenu;
    }

    public void setChildEduMenu(List<EduMenuDto> childEduMenu) {
        this.childEduMenu = childEduMenu;
    }
}
