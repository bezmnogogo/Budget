
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
	<nav>
		<a href="<c:url value="/main"/> ">Главная</a>
		<a href="<c:url value="/records/Overview/"/>">Обзор расходов</a>
		<a href="<c:url value="/categories/"/>">Категории</a>
		<a href="<c:url value="/records/mounthlyRecords"/>">По времени</a>
		<a href="<c:url value="/privateRoom/"/>">Личный кабинет</a>
		<a href="<c:url value="/cards/getRecords/"/>">Расходы по карте</a>
		<a href="<c:url value="/logout"/>">Выйти(${pageContext.request.userPrincipal.name})</a>
	</nav>
		<form method="post" action="<c:url value="/addCard"/> ">
				<h1>Добавить карту</h1>
				<input type="number" name="cardNumber" placeholder="Введите номер карты">
			    <input type="number" name="cash" placeholder="Введите остаток">
				<input type="submit" name="add_kard" value="Добавить">
		</form>
	</body>
</html>