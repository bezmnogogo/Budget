
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
	<nav>
		<a href="<c:url value="/main"/> ">Главная</a>
		<a href="<c:url value="/records/Overview/"/>">Обзор расходов</a>
		<a href="<c:url value="/categories/"/>">Категории</a>
		<a href="<c:url value="/records/mounthlyRecords"/>">По времени</a>
		<a href="<c:url value="/privateRoom/"/>">Личный кабинет</a>
		<a href="<c:url value="/cards/getRecords/"/>">Расходы по карте</a>
		<a href="<c:url value="/logout"/>">Выйти(${user.getUsername()})</a>
	</nav>
		<form method="post" action="<c:url value=" "/> ">
				<h1>Добавить категорию</h1>
				<c:if test="${message != null}">
					<h3>${message}</h3>
				</c:if>
				<c:if test="${addedMessage != null}">
					<h3>${addedMessage}</h3>
				</c:if>
				<input type="text" name="category" placeholder="Введите новую категорю">
				<input type="submit" name="add_cat" value="Добавить">
		</form>
	</body>
</html>