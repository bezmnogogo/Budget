
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<title>Категории</title>
	<link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/style.css"/>"/>
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
<section class="category">
	<h2>Категории:</h2>
	<ul>
		<c:forEach var="category" items="${categories}">
			<li><a href="<c:url value="/categories/getRecords/${category.getId()}/${year}/${intMounth}"/> ">${category.getType()}</a></li>
		</c:forEach>
	</ul>
</section>
<div class="add_button">
	<a href="<c:url value="/categories/addCategory"/>">Добавить +</a>
</div>
<div class="sort_mounth">
	<div class="arrow_left">
		<a href="<c:url value="/categories/getRecordsByMounth/prev/${categoryId == null ? -1 : categoryId}/${year}/${prevMounth}"/> " name="arrow_left">предыдущий месяц</a>
	</div>
	<div class="mounths">
		<p>${mounth} ${year} год</p>
	</div>
	<div class="arrow_right">
		<a href="<c:url value="/categories/getRecordsByMounth/next/${categoryId == null ? -1 : categoryId}/${year}/${nextMounth}"/>" name="arrow_right">следующий месяц</a>
	</div>
</div>
<section class="content pay_list">
	<h2>Список расходов ${strCategory}:</h2>
	<ul>
		<c:if test="${records != null}">
			<c:forEach var="record" items="${records}">
				<li>${record.toString()}</li>
			</c:forEach>
		</c:if>
	</ul>
</section>
<footer></footer>
</body>
</html>