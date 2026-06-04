package com.menu.model;

import java.util.List;

public interface MenuDAO_interface {
    void         insert(MenuVO vo);
    void         update(MenuVO vo);
    void         delete(Integer itemId);
    MenuVO       findByPrimaryKey(Integer itemId);
    List<MenuVO> getAll();
}
