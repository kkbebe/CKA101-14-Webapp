<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>修改餐點</title>

</head>
<body>
<div class="box">
  <h2>✏️ 修改餐點</h2>

  <c:if test="${not empty errors}">
  <ul class="error-list">
    <c:forEach var="e" items="${errors}"><li>${e}</li></c:forEach>
  </ul>
  </c:if>

  <form method="post" action="<%=request.getContextPath()%>/menu/menu.do">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="itemId" value="${menuVO.itemId}">

    <label>餐點編號</label>
    <input type="text" value="${menuVO.itemId}" readonly>
    <br>
    <label>分類編號 *</label>
    <input type="number" name="categoryId" value="${menuVO.categoryId}" min="1">
	<br>
    <label>餐點名稱 *</label>
    <input type="text" name="itemName" value="${menuVO.itemName}" maxlength="50">
	<br>
    <label>餐點描述</label>
    <textarea name="itemDesc">${menuVO.itemDesc}</textarea>
	<br>
    <label>價格 *</label>
    <input type="number" name="price" value="${menuVO.price}" step="0.01" min="0">
	<br>
    <label>圖片網址</label>
    <input type="text" name="imageUrl" value="${menuVO.imageUrl}" id="imgInput"
           oninput="previewImg(this.value)">
    <c:if test="${not empty menuVO.imageUrl}">
      <img id="imgPreview" src="${menuVO.imageUrl}" class="img-preview" alt="預覽">
    </c:if>
    <c:if test="${empty menuVO.imageUrl}">
      <img id="imgPreview" src="" class="img-preview" alt="預覽" style="display:none">
    </c:if>

    <label>排序</label>
    <input type="number" name="sortOrder" value="${menuVO.sortOrder}" min="0">

    <button type="submit" class="btn-submit">確認修改</button>
  </form>
  <a href="<%=request.getContextPath()%>/menu/menu.do?action=getAll" class="back">← 返回列表</a>
</div>
<script>
  function previewImg(url) {
    var img = document.getElementById('imgPreview');
    if (url) { img.src = url; img.style.display = 'block'; }
    else      { img.style.display = 'none'; }
  }
</script>
</body>
</html>
