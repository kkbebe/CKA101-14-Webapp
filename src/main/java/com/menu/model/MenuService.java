package com.menu.model;

import java.util.List;

public class MenuService {

	private MenuDAO_interface dao = new MenuJDBCDAO();

	public void addMenu(MenuVO vo) {
		dao.insert(vo);
	}

	public void updateMenu(MenuVO vo) {
		dao.update(vo);
	}

	public void deleteMenu(Integer itemId) {
		dao.delete(itemId);
	}

	public MenuVO getOne(Integer itemId) {
		return dao.findByPrimaryKey(itemId);
	}

	public List<MenuVO> getAll() {
		return dao.getAll();
	}
}
