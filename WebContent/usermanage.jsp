<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理画面</title>
<link href="./css/usermanage.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
<!--

function check(str){


	if(window.confirm('アカウントを' + str + 'をします')){ // 確認ダイアログを表示
		return true; // 「OK」時は送信を実行
	}	else{ // 「キャンセル」時の処理
		window.alert('キャンセルされました'); // 警告ダイアログを表示
		return false; // 送信を中止
	}
}
// -->
</script>

</head>
<body>

<div class="usermenu" align="right">
<a href="add" class="add">テスト</a>
<a href="newuser" class="new">ユーザー新規登録</a>
<a href="./" class="home">ホーム画面</a>
</div>
<br />
<br />
<br />
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


<div class="userinformation">
<table border="1" height="700">
<tr>
	<th>ログインID</th>
	<th>氏名</th>
	<th>勤務地</th>
	<th>所属部署・役職</th>
	<th>編集</th>
	<th>アカウント停止・復活</th>
	<th>アカウント削除</th>
</tr>

	<c:forEach items="${userinformations}" var="information">

<tr align="center">
			<td><span class="id"><c:out value="${information.loginId }"/></span></td>
			<td><span class="name"><c:out value="${information.name }"/></span></td>
			<td><span class="branchId">
			<c:forEach items="${branchs}" var="branch">
				<c:if test="${information.branchId == branch.id }">
					<c:out value="${branch.name}"/>
				</c:if>
			</c:forEach></span></td>
			<td><span class="SectionId">
			<c:forEach items="${sections}" var="section">
				<c:if test="${information.sectionId == section.id }">
					<c:out value="${section.name}"/>
				</c:if>
			</c:forEach></span></td>


<td>

		<div class="setting">
			<form action="setting" method="get">
				<input type="hidden" name="editid" value="${information.id }">
				<input type="submit" value="編集" />
			</form>
		</div>

</td>

<td>
<c:if test="${loginUser.id != information.id }">
		<div class="isStopped">

			<form action="isStopped" method="post" onSubmit="return check('停止')">
				<input type="hidden" name="id" value="${information.id }">
				<c:if test="${information.isStopped == 1 }">
					<input type="submit" value="停止" />
					<input type="hidden" name="isStopped" value="0"/>
				</c:if>
			</form>
			<form action="isStopped" method="post" onSubmit="return check('復活')">
				<input type="hidden" name="id" value="${information.id }">
				<c:if test="${information.isStopped == 0 }">
					<input type="hidden" name="isStopped" value="1"/>
					<input type="submit" value="復活" />
				</c:if>
			</form>
		</div>
</c:if>
</td>

<td>
<c:if test="${loginUser.id != information.id }">
		<div class="userDelete">
			<form action="userDelete" method="post" onSubmit="return check('削除')">
				<input type="hidden" name="id" value="${information.id }">
				<input type="submit" value="削除" />
			</form>
		</div>
</c:if>
</td>

</tr>

	</c:forEach>

</table>
</div>

</body>
</html>