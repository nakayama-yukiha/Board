<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>

<title>ホーム</title>
<link href="./css/home.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/ui-lightness/jquery-ui.css" >

<script>
  $(function() {
    $("#datepicker").datepicker({
    	 maxDate: '0y',
    })
    $("#datepicker").change(function(){
    	$("#datepicker2").datepicker("option","minDate",$("#datepicker").val())
    })
	$("#datepicker2").datepicker({
		maxDate: '0y',
	  })
	});
</script>

</head>
<body>
<div class="utilty" align="right">
<c:if test="${loginUser.branchId == 1 && loginUser.sectionId == 1 }">
<a href="usermanage" class="kanri">ユーザー管理画面</a>
</c:if>
<a href="logout" class="kanri">ログアウト</a><br />
</div>
<center><h1>わったい菜　BBS</h1></center>

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




<div class="filterArea">
<form action="./" method="get">
	<div class="filtre-category">
		<label for="start">カテゴリ</label>
		<select name="category">
			<option value="allCategory">指定しない</option>
			<c:forEach items="${categorys}" var="category">
				<option value="${category}" <c:if test="${ reCategory == category}"> selected </c:if>><c:out value="${category}"></c:out></option>
			</c:forEach>
		</select>
		<label for="start">開始日</label>
		<input type="text" id="datepicker" name="first" value="${reFirst}">
		<label for="end">終了日</label>
		<input type="text" id="datepicker2" name="end" value="${reEnd}">
		<input type="submit" value="絞り込む">
		<input type="button" onclick="location.href='./'"value="すべて表示">
	</div>
	<div class="filter-calendar">

	</div>
</form>
</div>

<a href="NewText" class="newtext">新しい記事を書く</a><br />

<div class="main-contents">



<table width="1000" border="1" height="1200" class="type01">
	<c:forEach items="${contribute}" var="contribute">

<tr>
<td align="left">
<div class="contribute">
			<div class="account-name">
				<span class="name">名前：<c:out value="${contribute.name }"/></span>
			</div>
			<div class="title">件名：<c:out value="${contribute.title}"/></div>
			<div class="text"><pre><c:out value="${contribute.text}"/></pre></div>
			<div class="date">投稿日時：<fmt:formatDate value="${contribute.
			insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>

			<div class="category">カテゴリ：<c:out value="${contribute.category}"/></div>
			<c:if test="${loginUser.branchId == 1 && loginUser.sectionId == 2
				|| loginUser.sectionId == 3 && loginUser.branchId == contribute.branchId || loginUser.id == contribute.id}">
				<form action="contributeDelete" method="post">
					<input type="hidden" name="contributeId" value="${ contribute.textId}"/>
					<input type="hidden" name="contributeUser" value="${contribute.userId }">
					<input type="submit" value="この投稿を削除する">
				</form>
			</c:if>
</div>

<div class="comment">

	<form action="newComment" method="post">
		<textarea name="comment" cols="100" rows="5" onKeyup="o=document.getElementById('slen${contribute.textId }');
		n=this.value.length;
		o.value=n;o.innerHTML=n;
		o.style.color=(n>500)?'red':'forestgreen';
		document.getElementById('mes${contribute.textId}').innerHTML=(n>500)?'文字　文字制限を越えました':'文字';
		document.getElementById('mes2${contribute.textId}').innerHTML=(n>500)?'':'　あと、'+(500-n)+'文字入力できます。';"></textarea><br />
	<span id="slen${contribute.textId }" style="border:1px solid #9ab;width:3em"></span>
	<span id="mes${contribute.textId }">文字</span>
	<span id="mes2${contribute.textId }"></span>

		<input type="hidden" name="contributeId" value="${contribute.textId }"/>
		<input type="submit" value="コメントする">
	</form>
</div>


</td>
</tr>
<tr>
<td align="right" class="even">
		<div class="showcoment">
		<p>コメント一覧</p>
			<c:forEach items="${comments}" var="comment">
				<c:if test="${ contribute.textId == comment.contributeId }">

					<div class="account-name">
					<span class="name"><c:out value="${comment.name }"/></span>
					</div>
					<div class="comment"><pre><c:out value="${comment.comment}"/></pre></div>
					<div class="date"><fmt:formatDate value="${comment.
					insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
					<c:if test="${loginUser.branchId == 1 && loginUser.sectionId == 2
						|| loginUser.sectionId == 3 && loginUser.branchId == comment.branchId }">
						<form action="commentDelete" method="post">
							<input type="hidden" name="commentId" value="${ comment.commentId}"/>
							<input type="submit" value="コメントを削除する">

						</form>
					</c:if>
					***************************
				</c:if>
			</c:forEach>

		</div>

		<br />
		</td>
</tr>
	</c:forEach>

	</table>
</div>






</body>
</html>