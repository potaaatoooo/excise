
<html>
<head>
    <title>错误提示</title>
    <link href="./css/error.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" type="text/javascript" src="./js/powerError.js"></script>
</head>
<body onload="run()">
<div id="error_father">
    <div id="error_left"></div>
    <div id="error_right1">${sessionScope.get("errorMsg")}</div>
    <div id="error_right2">
        <p><span id="time">10</span>秒后自动返回到首页</p>
        <p>不能跳转，请<a href="main.jsp" id="a1">请点击这儿</a></p>
    </div>
</div>
</body>
</html>
