<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vo.Download" %><%--
  Created by IntelliJ IDEA.
  User: 13281
  Date: 2020/9/20
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>下载</title>
    <link href="./css/download.css" rel="stylesheet" />
</head>
<body>
    <div id="main_top">
        <div class="main_topLeft"><img src="images/main_left.png"></div>
        <div class="main_topRight1">欢迎你：<span id="role">${sessionScope.get("role")}</span><a class="a1" href="exit.do?id=exit" >【安全退出】 </a ></div>
        <div class="main_topRight2">
            <div class="main_class2"></div>
            <div class="main_class1"><a href="main.jsp" class="a2">首页</a></div>
            <div class="main_class1"><a href="downloadRequest.do?id=jump_download" class="a2">资源下载</a></div>
            <div class="main_class1"><a href="user.jsp" class="a2">用户管理</a></div>
            <div class="main_class1"><a href="resource.jsp" class="a2">资源管理</a></div>
            <div class="main_class1"><a href="person.jsp" class="a2">个人中心</a></div>
        </div>
    </div>

</body>
</html>
