<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー情報編集画面</title>
<link href="./css/setting.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class=main-contents>
<center><h3>ユーザー情報の編集ができます</h3></center>
<table>
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
</table>
<form action="setting" method="post" ><br />
	<label for="editLoginId">ログインID</label><br />
	<input name="editLoginId" value="${editUser.loginId}" id="editLoginId"/><br />
	(英数字10～20文字以内で入力してください）<br />

	<label for="editPassword">パスワード</label><br />
	<input name="editPassword" type="password" id="editPassword" /><br />
	(記号を含む英数字6文字以上で入力してください）<br />

	<label for="editRePassword">確認用パスワード</label><br />
	<input name="editRePassword" type="password" id="editRePassword"/> <br />
	(確認のためもう一度同じパスワードを入力してください）<br />

	<label for="editNname">名前</label><br />
	<input name="editName" value="${editUser.name}" id="editName"/> <br />

	<label for="editBranchId">勤務地</label><br />
<!-- ログインユーザーと編集者が一緒の場合 -->
	<c:if test="${editUser.id == loginUser.id }">
		<input name="editBranchId" type="text" <c:forEach items="${branch}" var="branch">
		<c:if test="${branch.id == editUser.branchId }">value="<c:out value="${branch.name}"></c:out>"</c:if></c:forEach> disabled/>
		<input type="hidden" name="editBranchId" value="${editUser.branchId}"/><br />
	</c:if>
<!-- ログインユーザーと編集者が違う場合 -->
	<c:if test="${editUser.id != loginUser.id }">
	<select name="editBranchId" id="editBranchId">

	<c:forEach items="${branch}" var="branch">
		<option value="${branch.id}" <c:if test="${ editUser.branchId == branch.id}"> selected </c:if>><c:out value="${branch.name}"></c:out></option>
	</c:forEach>
	</select><br />
	</c:if>

	<label for="editSectionId">部署・役職</label><br />
<!-- ログインユーザーと編集者が一緒の場合 -->
	<c:if test="${editUser.id == loginUser.id }">
		<input name="editSectionId" type="text" <c:forEach items="${section}" var="section">
		<c:if test="${section.id == editUser.sectionId }">value="<c:out value="${section.name}"></c:out>"</c:if></c:forEach> disabled/>
		<input type="hidden" name="editSectionId" value="${editUser.sectionId}"/>
	</c:if>


<!-- ログインユーザーと編集者が違う場合 -->
	<c:if test="${editUser.id != loginUser.id }">
	<select name="editSectionId" id="editSectionId">
	<c:forEach items="${section}" var="section">

			<option value="${section.id}" <c:if test="${ editUser.sectionId == section.id}"> selected </c:if>><c:out value="${section.name}"></c:out></option>>

	</c:forEach>

	</select>
	</c:if>
	<br />

	<input type="hidden" name="editUser" value="${editUser}"/>
	<input type="hidden" name="checkLoginId" value="${editUser.id}"/>
<br />
	<input type="submit" value="登録" /> <br /><br />
	<a href="usermanage">戻る</a>
</form>
<div class="copyright">Copyright(c)Yukiha Nakayama</div>
</div>
</body>

</html>