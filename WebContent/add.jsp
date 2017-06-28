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
<script src="http://code.jquery.com/ui/1.12.0-rc.2/jquery-ui.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>

<title>追加画面</title>
<link href="./css/add.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/ui-lightness/jquery-ui.css" >


</head>
<body>
<a href="#">Click Me!</a>
<br /><br />
<div id="click-event">
  ここをクリックすると…
</div>
<script>
$("a").click(function(){
  alert("aタグ .click() のイベントだよぉ〜！");
});
$("#click-event").click(function(){
  $(this).slideUp();
});
</script>


<br />
<br />
<div id="rensyu">ここをクリックすると～</div>

<script>


$('#rensyu').on('click',function() {
	alert("クリックされました");
});






</script>
<br />
<br />
<div class="box-container">
	<div class="box">
		<table border="1">
		<tr>
		<th>勤務地</th>
		</tr>
		<tr>
		<td>
			<form action="add" method="post">
				<input type="text" name="branchName" />
				<input type="submit" value="追加する">
			</form>
		</td>
		</tr>
		<tr>
		<td>
			<form action="add" method="post">
				<select name="branchId" id="branchId">
				<option value="">選択してください</option>
				<c:forEach items="${branches}" var="branch">
					<option value="${branch.id}"><c:out value="${branch.name}"></c:out></option>
				</c:forEach>
				</select>
				<input type="submit" value="削除する">
			</form>
		</td>
		</tr>
		<c:forEach items="${branches}" var="branch">
			<tr><td><p id="button"><c:out value="${branch.name}" /></p></td></tr>
		</c:forEach>
		</table >
	</div>

	<div class="box">
		<table border="1">
		<tr>
		<th>部署・役職</th>
		</tr>
		<tr>
		<td>
			<form action="add" method="post">
				<input type="text" name="sectionName" />
				<input type="submit" value="追加する">
			</form>
		</td>
		<tr>
		<td>
			<form action="add" method="post">
				<select name="sectionId" id="sectionId">
				<option value="">選択してください</option>
				<c:forEach items="${sections}" var="section">
					<option value="${section.id}"><c:out value="${section.name}"></c:out></option>
				</c:forEach>
				</select>
				<input type="submit" value="削除する">
			</form>
		</td>
		</tr>
		<c:forEach items="${sections}" var="section">
		<tr><td><p id="button"><c:out value="${section.name}" /></p></td></tr>
		</c:forEach>
		</table>
	</div>
</div>

<div id="dialog" title="編集">
	<form>
		<p>編集後の名称を入れて編集ボタンを押してください。</p>
		<input type="text" name="setting" id=setting/>
	</form>
</div>

<script>
$(function(){
	$('#dialog').dialog({
		autoOpen: false,
		modal: true,
		buttons: {
	            '編集': function() {
	                jQuery( this ) . dialog( 'close' );
	            },
	            'キャンセル': function() {
	                jQuery( this ) . dialog( 'close' );
	            },
	        }
	});
	$('[id=button]').on('click',function(){
		$('#dialog').dialog('open');

	});
});


</script>



<br />
<br />
<br />
<a href="./" class="home">戻る</a>
</body>
</html>