package com.menu.model;

import java.sql.*;
import java.util.*;

public class MenuJDBCDAO implements MenuDAO_interface {

    // ↓ 改這裡：直接寫死 JDBC 連線資訊
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // 依你的DB調整
    private static final String URL    = "jdbc:mysql://localhost:3306/restaurant?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
    private static final String USER   = "root";
    private static final String PASS   = "123456";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("找不到 JDBC Driver", e);
        }
    }

    // ↓ 取代原本的 dataSource.getConnection()
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    @Override
    public void insert(MenuVO vo) {
        String sql = "INSERT INTO RESTAURANT_MENU "
                   + "(CATEGORY_ID, ITEM_NAME, ITEM_DESC, PRICE, IMAGE_URL, SORT_ORDER) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt   (1, vo.getCategoryId());
            ps.setString(2, vo.getItemName());
            ps.setString(3, vo.getItemDesc());
            ps.setDouble(4, vo.getPrice());
            ps.setString(5, vo.getImageUrl());
            ps.setInt   (6, vo.getSortOrder());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("新增失敗", e);
        }
    }

    @Override
    public void update(MenuVO vo) {
        String sql = "UPDATE RESTAURANT_MENU "
                   + "SET CATEGORY_ID=?, ITEM_NAME=?, ITEM_DESC=?, PRICE=?, IMAGE_URL=?, SORT_ORDER=? "
                   + "WHERE ITEM_ID=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt   (1, vo.getCategoryId());
            ps.setString(2, vo.getItemName());
            ps.setString(3, vo.getItemDesc());
            ps.setDouble(4, vo.getPrice());
            ps.setString(5, vo.getImageUrl());
            ps.setInt   (6, vo.getSortOrder());
            ps.setInt   (7, vo.getItemId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("修改失敗", e);
        }
    }

    @Override
    public void delete(Integer itemId) {
        String sql = "DELETE FROM RESTAURANT_MENU WHERE ITEM_ID=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, itemId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("刪除失敗", e);
        }
    }

    @Override
    public MenuVO findByPrimaryKey(Integer itemId) {
        String sql = "SELECT * FROM RESTAURANT_MENU WHERE ITEM_ID=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, itemId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) {
            throw new RuntimeException("查詢失敗", e);
        }
        return null;
    }

    @Override
    public List<MenuVO> getAll() {
        String sql = "SELECT * FROM RESTAURANT_MENU ORDER BY SORT_ORDER, ITEM_ID";
        List<MenuVO> list = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("查詢全部失敗", e);
        }
        return list;
    }

    private MenuVO mapRow(ResultSet rs) throws SQLException {
        MenuVO vo = new MenuVO();
        vo.setItemId    (rs.getInt   ("ITEM_ID"));
        vo.setCategoryId(rs.getInt   ("CATEGORY_ID"));
        vo.setItemName  (rs.getString("ITEM_NAME"));
        vo.setItemDesc  (rs.getString("ITEM_DESC"));
        vo.setPrice     (rs.getDouble("PRICE"));
        vo.setImageUrl  (rs.getString("IMAGE_URL"));
        vo.setSortOrder (rs.getInt   ("SORT_ORDER"));
        return vo;
    }
}