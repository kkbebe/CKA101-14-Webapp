<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.MenuJDBCDAO, com.menu.model.MenuVO, java.util.*" %>

<html>
<head>
<title>餐廳菜單管理</title>

</head>
<body>
<div class="container">
  <div class="top-bar">
    <h2>🍜 餐廳菜單管理</h2>
    <a href="<%=request.getContextPath()%>/menu/menu.do?action=toAdd" class="btn-add">＋ 新增餐點</a>
  </div>

  <table>
    <tr>
      <th>編號</th><th>分類ID</th><th>餐點名稱</th><th>描述</th>
      <th>價格</th><th>圖片</th><th>排序</th><th>修改</th><th>刪除</th>
    </tr>
    <c:forEach var="vo" items="${list}">
    <tr>
      <td>${vo.itemId}</td>
      <td>${vo.categoryId}</td>
      <td>${vo.itemName}</td>
      <td>${vo.itemDesc}</td>
      <td>$${vo.price}</td>
      <td>
        <c:if test="${not empty vo.imageUrl}">
          <img src="${vo.imageUrl}" class="img-thumb" alt="圖片">
        </c:if>
        <c:if test="${empty vo.imageUrl}">—</c:if>
      </td>
      <td>${vo.sortOrder}</td>
      <td>
        <form method="post" action="<%=request.getContextPath()%>/menu/menu.do" style="margin:0">
          <input type="hidden" name="action" value="getOne_For_Update">
          <input type="hidden" name="itemId" value="${vo.itemId}">
          <button type="submit" class="btn btn-edit">修改</button>
        </form>
      </td>
      <td>
        <form method="post" action="<%=request.getContextPath()%>/menu/menu.do" style="margin:0"
              onsubmit="return confirm('確定刪除「${vo.itemName}」？')">
          <input type="hidden" name="action" value="delete">
          <input type="hidden" name="itemId" value="${vo.itemId}">
          <button type="submit" class="btn btn-delete">刪除</button>
        </form>
      </td>
    </tr>
    </c:forEach>
    <c:if test="${empty list}">
      <tr><td colspan="9" style="color:#999">尚無餐點資料</td></tr>
    </c:if>
  </table>
  <a href="<%=request.getContextPath()%>/menu/menu.do" class="btn-add">← 回首頁</a>
</div>
</body>
</html>
