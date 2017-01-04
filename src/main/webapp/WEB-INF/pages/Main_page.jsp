
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
		</nav>
		<div class="last_expenses">
			<h2>Последние траты</h2>
			<ul>
				<c:forEach var="record" items="${records}">
					<li>${record.toString()}</li>
				</c:forEach>
			</ul>
			<a href="<c:url value="/records/addPaidRecord"/> " class="add_enemy">Добавить трату+</a>
		</div>
		<div class="near_expenses">
			<h2> Ближайшие запланированные расходы</h2>
			<ul>
				<c:forEach var="plannedRecord" items="${plannedRecords}">
					<li>${plannedRecord.toString()}</li>
				</c:forEach>
			</ul>
			<a href="<c:url value="/records/addPlannedRecord"/> " class="add_enemy">Добавить расход+</a>
		</div>
		<div class="information">
			<h2> Информация о картах</h2>
			<ul>
				<c:choose>
					<c:when test="${cards == null}">
						<li>У Вас нет карт</li>
					</c:when>
					<c:otherwise>
						<c:forEach var="card" items="${cards}">
							<li>${card.toString()}</li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</ul>
			<a href="<c:url value="/addCard"/>" class="add_enemy">Добавить карту+</a>
		</div>
		<div class="for_parsing">
			<h2> Курсы валют</h2>
			<ul>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
			<input type="file" value="Добавить файл для парсинга">
		</div>
	</body>
</html>