<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>餐廳菜單管理: Home</title>

<style>

    </style>
</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>餐廳菜單管理: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for 餐廳菜單管理</p>

<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<jsp:useBean id="menuSvc" scope="page" class="com.menu.model.MenuService" />

<ul>
  <%-- 列出全部 --%>
  <li><a href='menu.do?action=getAll'>List</a> all Menus.<br><br></li>

  <%-- 依餐點編號查詢 --%>
  <li>
    <form method="post" action="menu.do">
        <b>輸入餐點編號:</b>
        <input type="text" name="itemId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </form>
  </li>

  <%-- 下拉選餐點編號 --%>
  <li>
    <form method="post" action="menu.do">
        <b>選擇餐點編號:</b>
        <select size="1" name="itemId">
            <c:forEach var="menuVO" items="${menuSvc.all}">
                <option value="${menuVO.itemId}">${menuVO.itemId}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </form>
  </li>

  <%-- 下拉選餐點名稱 --%>
  <li>
    <form method="post" action="menu.do">
        <b>選擇餐點名稱:</b>
        <select size="1" name="itemId">
            <c:forEach var="menuVO" items="${menuSvc.all}">
                <option value="${menuVO.itemId}">${menuVO.itemName}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </form>
  </li>
</ul>

<h3>菜單管理</h3>
<ul>
  <li><a href='menu.do?action=toAdd'>Add</a> a new Menu.</li>
</ul>

</body>
</html>