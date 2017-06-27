<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿画面</title>
<link href="./css/newtext.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="main-contents">
<h3>新規投稿ができます</h3>

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

<form action="NewText" method="post">
<div class="title"><label for="title">件名</label><br />
<input type="text" name="title" id="title" size="30" value="${reTitle }"></div><br />
<div class="category"><label for="category">カテゴリー</label><br />
<select name="category">
	<option value="">新規追加する</option>
	<c:forEach items="${categorys}" var="category">
		<option value="${category}" <c:if test="${ reCategory == category}"> selected </c:if>><c:out value="${category}"></c:out></option>
	</c:forEach>
</select>
<input type="text" name="category2" id="category2" size="15" value="${reCategory2}">
</div>
<label for="text">本文</label><br />
<textarea name="text" cols="80" rows="10" onKeyup="
o=document.getElementById('slen');
n=this.value.length;
o.value=n;
o.innerHTML=n;
o.style.color=(n>100)?'red':'forestgreen';
document.getElementById('mes').innerHTML=(n>1000)?'文字　文字制限を越えました':'字';
document.getElementById('mes2').innerHTML=(n>1000)?'':'　あと、'+(1000-n)+'文字です。';
">${reText }</textarea><br />
<span id="slen" style="border:1px solid #9ab;width:3em">
</span>
<span id="mes">文字</span>
<span id="mes2"></span>
<br />
<input type="submit" value="投稿する"/>
</form>
<br />
<br />
<a href="./">ホームへ戻る</a>
</div>
</body>
</html>