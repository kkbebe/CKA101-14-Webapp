<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.menu.model.*"%>

<%
  MenuVO menuVO = (MenuVO) request.getAttribute("menuVO");
%>

<html>
<head>
<title>餐點資料 - listOneMenu.jsp</title>

<style>
  table#table-1 {
    background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
    width: 600px;
    background-color: white;
    margin-top: 5px;
    margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁採用 Script 的寫法取值:</h4>
<table id="table-1">
  <tr><td>
    <h3>餐點資料 - listOneMenu.jsp</h3>
    <h4><a href="menu.do">← 回首頁</a></h4>
  </td></tr>
</table>

<table>
  <tr>
    <th>餐點編號</th>
    <th>分類ID</th>
    <th>餐點名稱</th>
    <th>描述</th>
    <th>價格</th>
    <th>圖片網址</th>
    <th>排序</th>
  </tr>
  <tr>
    <td><%=menuVO.getItemId()%></td>
    <td><%=menuVO.getCategoryId()%></td>
    <td><%=menuVO.getItemName()%></td>
    <td><%=menuVO.getItemDesc()%></td>
    <td><%=menuVO.getPrice()%></td>
    <td><%=menuVO.getImageUrl()%></td>
    <td><%=menuVO.getSortOrder()%></td>
  </tr>
</table>

</body>
</html>