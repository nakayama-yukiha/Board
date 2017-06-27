<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新規ユーザー登録</title>
	<link href="./css/setting.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">
<h3>ユーザーの新規登録ができます</h3>
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<c:out value="${message}" /><br />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>


<form action="newuser" method="post"><br />

	<label for="loginId">ログインID</label><br />
	<input name="loginId" id="loginId" value="${reLoginId }"/><br />（英数字10～20文字以内で入力してください）<br />

	<label for="password">パスワード</label><br />
	<input name="password" type="password" id="password"/><br />（記号を含む英数字6文字以上で入力してください）<br />

	<label for="repassword">確認用パスワード</label><br />
	<input name="repassword" type="password" id="repassword"/><br />
	（確認のためもう一度同じパスワードを入力してください）<br />

	<label for="name">氏名</label><br />
	<input name="name"  id="name" value="${reName }"/> <br />

	<label for="branchId">勤務地</label><br />
	<select name="branchId" id="branchId">
	<c:if test="${ reBranchId == 0}">
		<option value="0">選択してください</option>
	</c:if>

	<c:forEach items="${branch}" var="branch">
	<option value="${branch.id}" <c:if test="${ reBranchId == branch.id}"> selected </c:if>><c:out value="${branch.name}"></c:out></option>
	</c:forEach>
	</select><br />

	<label for="sectionId">部署・役職</label><br />
	<select name="sectionId" id="sectionId">
	<c:if test="${ reSectionId == 0}">
		<option value="0">選択してください</option>
	</c:if>
	<c:forEach items="${section}" var="section">
	<option value="${section.id}" <c:if test="${ reSectionId == section.id}"> selected </c:if>><c:out value="${section.name}"></c:out></option>
	</c:forEach>
	</select><br />
	<br />
	<br />
	<input type="hidden" name="isStopped" value="1"/>

	<input type="submit" value="登録" /> <br /><br />
	<a href="usermanage">戻る</a>
	<div class="copyright">Copyright(c)Yukiha Nakayama</div>
<div class="branch">

</div>
</form>
</div>

</body>
</html>