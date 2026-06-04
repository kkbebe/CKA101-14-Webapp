package com.menu.controller;

import java.io.*;
import java.util.*;
import com.menu.model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//test
public class MenuServlet extends HttpServlet {

	private MenuService svc = new MenuService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if (action == null)
			action = "d";

		switch (action) {
		case "getAll":
			getAll(req, res);
			break;
		case "toAdd":
			toAdd(req, res);
			break;
		case "insert":
			insert(req, res);
			break;
		case "getOne_For_Update":
			getOne_For_Update(req, res);
			break;
		case "update":
			update(req, res);
			break;
		case "delete":
			delete(req, res);
			break;
		case "getOne_For_Display":
		    getOne_For_Display(req, res);
		    break;
		case "toSelect":
		    toSelect(req, res);
		    break;
		default:
		    toSelect(req, res);
		}
	}
	
	
	
	private void toSelect(HttpServletRequest req, HttpServletResponse res)
	        throws ServletException, IOException {
	    req.getRequestDispatcher("/menu/selectMenu.jsp").forward(req, res);
	}
	
	
	// ── 查單筆顯示 ───────────────────────────────────────────
	private void getOne_For_Display(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	    List<String> errorMsgs = new ArrayList<>();
	    req.setAttribute("errorMsgs", errorMsgs);

	    String str = req.getParameter("itemId");
	    if (str == null || str.trim().isEmpty()) {
	        errorMsgs.add("請輸入餐點編號");
	        req.getRequestDispatcher("/menu/selectMenu.jsp").forward(req, res);
	        return;
	    }

	    Integer itemId = null;
	    try {
	        itemId = Integer.valueOf(str.trim());
	    } catch (NumberFormatException e) {
	        errorMsgs.add("餐點編號格式不正確");
	        req.getRequestDispatcher("/menu/selectMenu.jsp").forward(req, res);
	        return;
	    }

	    MenuVO menuVO = svc.getOne(itemId);
	    if (menuVO == null) {
	        errorMsgs.add("查無此餐點");
	        req.getRequestDispatcher("/menu/selectMenu.jsp").forward(req, res);
	        return;
	    }

	    req.setAttribute("menuVO", menuVO);
	    req.getRequestDispatcher("/menu/selectOneMenu.jsp").forward(req, res);
	}

	// ── 列表 ────────────────────────────────────────────────
	private void getAll(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("list", svc.getAll());
		req.getRequestDispatcher("/menu/listAllMenu.jsp").forward(req, res);
	}

	// ── 進新增頁 ─────────────────────────────────────────────
	private void toAdd(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/menu/addMenu.jsp").forward(req, res);
	}

	// ── 新增 ────────────────────────────────────────────────
	private void insert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String categoryIdStr = req.getParameter("categoryId");
		String itemName = req.getParameter("itemName");
		String itemDesc = req.getParameter("itemDesc");
		String priceStr = req.getParameter("price");
		String imageUrl = req.getParameter("imageUrl");
		String sortOrderStr = req.getParameter("sortOrder");

		List<String> errors = validate(categoryIdStr, itemName, priceStr, sortOrderStr);

		if (!errors.isEmpty()) {
			req.setAttribute("errors", errors);
			req.setAttribute("categoryId", categoryIdStr);
			req.setAttribute("itemName", itemName);
			req.setAttribute("itemDesc", itemDesc);
			req.setAttribute("price", priceStr);
			req.setAttribute("imageUrl", imageUrl);
			req.setAttribute("sortOrder", sortOrderStr);
			req.getRequestDispatcher("/menu/addMenu.jsp").forward(req, res);
			return;
		}

		MenuVO vo = buildVO(null, categoryIdStr, itemName, itemDesc, priceStr, imageUrl, sortOrderStr);
		svc.addMenu(vo);
		res.sendRedirect(req.getContextPath() + "/menu/menu.do?action=getAll");
	}

	// ── 進修改頁 ─────────────────────────────────────────────
	private void getOne_For_Update(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		req.setAttribute("menuVO", svc.getOne(itemId));
		req.getRequestDispatcher("/menu/updateMenu.jsp").forward(req, res);
	}

	// ── 修改 ────────────────────────────────────────────────
	private void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String itemIdStr = req.getParameter("itemId");
		String categoryIdStr = req.getParameter("categoryId");
		String itemName = req.getParameter("itemName");
		String itemDesc = req.getParameter("itemDesc");
		String priceStr = req.getParameter("price");
		String imageUrl = req.getParameter("imageUrl");
		String sortOrderStr = req.getParameter("sortOrder");

		List<String> errors = validate(categoryIdStr, itemName, priceStr, sortOrderStr);

		if (!errors.isEmpty()) {
			MenuVO vo = buildVO(Integer.parseInt(itemIdStr), categoryIdStr, itemName, itemDesc, priceStr, imageUrl,
					sortOrderStr);
			req.setAttribute("errors", errors);
			req.setAttribute("menuVO", vo);
			req.getRequestDispatcher("/menu/updateMenu.jsp").forward(req, res);
			return;
		}

		MenuVO vo = buildVO(Integer.parseInt(itemIdStr), categoryIdStr, itemName, itemDesc, priceStr, imageUrl,
				sortOrderStr);
		svc.updateMenu(vo);
		res.sendRedirect(req.getContextPath() + "/menu/menu.do?action=getAll");
	}

	// ── 刪除 ────────────────────────────────────────────────
	private void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		svc.deleteMenu(itemId);
		res.sendRedirect(req.getContextPath() + "/menu/menu.do?action=getAll");
	}

	// ── 共用：驗證 ──────────────────────────────────────────
	private List<String> validate(String categoryIdStr, String itemName, String priceStr, String sortOrderStr) {
		List<String> errors = new ArrayList<>();

		if (categoryIdStr == null || categoryIdStr.trim().isEmpty())
			errors.add("分類編號不能為空");
		else {
			try {
				Integer.parseInt(categoryIdStr.trim());
			} catch (NumberFormatException e) {
				errors.add("分類編號必須為整數");
			}
		}

		if (itemName == null || itemName.trim().isEmpty())
			errors.add("餐點名稱不能為空");
		else if (itemName.trim().length() > 50)
			errors.add("餐點名稱不能超過 50 個字");

		if (priceStr == null || priceStr.trim().isEmpty())
			errors.add("餐點價格不能為空");
		else {
			try {
				double p = Double.parseDouble(priceStr.trim());
				if (p < 0)
					errors.add("價格不能為負數");
			} catch (NumberFormatException e) {
				errors.add("價格格式不正確");
			}
		}

		if (sortOrderStr != null && !sortOrderStr.trim().isEmpty()) {
			try {
				Integer.parseInt(sortOrderStr.trim());
			} catch (NumberFormatException e) {
				errors.add("排序必須為整數");
			}
		}
		return errors;
	}

	// ── 共用：組 VO ─────────────────────────────────────────
	private MenuVO buildVO(Integer itemId, String categoryIdStr, String itemName, String itemDesc, String priceStr,
			String imageUrl, String sortOrderStr) {
		MenuVO vo = new MenuVO();
		if (itemId != null)
			vo.setItemId(itemId);
		vo.setCategoryId(Integer.parseInt(categoryIdStr.trim()));
		vo.setItemName(itemName.trim());
		vo.setItemDesc(itemDesc == null || itemDesc.trim().isEmpty() ? null : itemDesc.trim());
		vo.setPrice(Double.parseDouble(priceStr.trim()));
		vo.setImageUrl(imageUrl == null || imageUrl.trim().isEmpty() ? null : imageUrl.trim());
		vo.setSortOrder(
				sortOrderStr == null || sortOrderStr.trim().isEmpty() ? 0 : Integer.parseInt(sortOrderStr.trim()));
		return vo;
	}
}
