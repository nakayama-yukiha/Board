<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>ログイン</title>
<link href="./css/login.css" rel="stylesheet" type="text/css">
</head>
<body>



<div class="main-contents">

<h1>わったい菜 BBS</h1>

<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<form action="login" method="post"><br />
	<p><label for="loginId">ログインID</label></p>
	<input name="loginId" id="loginId" value="${reLoginId }"/> <br />

	<p><label for="password">パスワード</label></p>
	<input name="password" type="password" id="password" value="${rePassword}"/> <br />
<br />
	<input id="submit" type="submit" value="ログイン" /> <br />

</form>

<div class="copyright"><p>Copyright(c)Yukiha Nakayama</p></div>

</div>
</body>
</html>