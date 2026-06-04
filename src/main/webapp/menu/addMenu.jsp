<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>新增餐點</title>

</head>
<body>
<div class="box">
  <h2>新增餐點</h2>

  <c:if test="${not empty errors}">
  <ul class="error-list">
    <c:forEach var="e" items="${errors}"><li>${e}</li></c:forEach>
  </ul>
  </c:if>

  <form method="post" action="<%=request.getContextPath()%>/menu/menu.do">
    <input type="hidden" name="action" value="insert">

    <label>分類編號 *</label>
    <input type="number" name="categoryId" value="${categoryId}" min="1" placeholder="輸入分類ID">
	<br>
    <label>餐點名稱 *</label>
    <input type="text" name="itemName" value="${itemName}" maxlength="50" placeholder="例：牛肉麵">
	<br>
    <label>餐點描述</label>
    <textarea name="itemDesc">${itemDesc}</textarea>
	<br>
    <label>價格 *</label>
    <input type="number" name="price" value="${price}" step="0.01" min="0" placeholder="例：150">
	<br>
    <label>圖片網址</label>
    <input type="text" name="imageUrl" value="${imageUrl}" placeholder="https://...（可不填）">
	<br>
    <label>排序</label>
    <input type="number" name="sortOrder" value="${empty sortOrder ? 0 : sortOrder}" min="0">
	<br>
    <button type="submit" class="btn-submit">確認新增</button>
  </form>
  <a href="<%=request.getContextPath()%>/menu/menu.do?action=getAll" class="back">← 返回列表</a>
  <br>
  <a href="<%=request.getContextPath()%>/menu/menu.do?action=toSelect" class="back">← 回首頁</a>
</div>
</body>
</html>
