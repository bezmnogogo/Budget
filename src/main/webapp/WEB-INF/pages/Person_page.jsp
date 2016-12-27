<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
	<head>
		<title></title>
	</head>
	<body>
		<h1> Личный кабинет</h1>
		<form method="post" action="<c:url value="/private-room/action/changeUsername"/> ">
		<div class="person_name"> 
			<p>Логин</p>
			<input type="text" name="p_name" value="${user.username}">
			<button type="submit">изменить</button>
		</div>
		</form>
		<form method="post" action="<c:url value="/private-room/action/changeMail "/> ">
		<div class="person_email">
			<p>E-mail</p>
			<input type="e-mail" name="p_email" value="${user.mail}">
			<button type="submit">Изменить</button>
		</div>
		</form>
		<form method="post" action="<c:url value=" /private-room/action/changeLimit"/> ">
		<div class="person_limit">
			<p>Лимит</p>
			<input type="text" name="p_limit" value="${user.mounthlyLimit}">
			<button type="submit">Изменить</button>
		</div>
		</form>
	</body>
</html>
