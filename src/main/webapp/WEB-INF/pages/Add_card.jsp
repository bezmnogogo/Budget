<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
	<head>
		<title>Просмотр расходов</title>
	</head>
	<body>
		<form method="post" action="<c:url value="/addCard"/> ">
				<h1>Добавить карту</h1>
				<input type="number" name="cardNumber" placeholder="Введите номер карты">
				<input type="submit" name="add_kard" value="Добавить">
		</form>
	</body>
</html>