
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<title>Просмотр запланированных расходов</title>
	<link rel="stylesheet" href="<c:url value="/resources/css2/style.css"/>"/>
</head>
<body>
<header>
	<nav>
		<a href="<c:url value="/main"/> ">Главная</a>
		<a href="<c:url value="/records/Overview/"/>">Обзор расходов</a>
		<a href="<c:url value="/categories/"/>">Категории</a>
		<a href="<c:url value="/records/mounthlyRecords"/>">По времени</a>
		<a href="<c:url value="/privateRoom/"/>">Личный кабинет</a>
		<a href="<c:url value="/cards/getRecords/"/>">Расходы по карте</a>
		<a href="<c:url value="/logout"/>">Выйти(${pageContext.request.userPrincipal.name})</a>
	</nav>
</header>
<form method="post" action="<c:url value="/records//changePlannedRecord "/> ">
	<section class="planed_box">
		<ul class="about_planed_list">
			<li>Расход</li>
			<li>Сумма</li>
			<li>Категории</li>
			<li>Карта</li>
			<li>Дата траты</li>
			<li>Интервал</li>
		</ul>
		<ul class="list_planed">
			<li><textarea rows="1" cols="20" name="pay">Запланированный</textarea> ${message}</li>
			<li><textarea rows="1" cols="20" name="sum">${plannedRecord.getSum()}</textarea></li>
			<li>
				<select required name="selectedCategory">
					<c:forEach var="category" items="${categories}">
						<c:choose>
							<c:when test="${category.getType() == plannedRecord.getCategory().getType()}">
								<option selected value="${category.getType()}">${category.getType()}</option>
							</c:when>
							<c:otherwise>
								<option value="${category.getType()}">${category.getType()}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</li>
			<li>
				<select name="selectedCard">
					<c:forEach var="card" items="${cards}">
						<c:choose>
							<c:when test="${cards == null}">
								<option selected disabled>У вас нет карт</option>
							</c:when>
							<c:when test="${card.getCardNumber() == plannedRecord.getCard().getCardNumber()}">
								<option selected value="${card.getCardNumber()}">${card.getCardNumber()}</option>
							</c:when>
							<c:otherwise>
								<option value="${card.getCardNumber()}">${card.getCardNumber()}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</li>
			<li><input type="date" value="${plannedRecord.getStartDate()}" name="recordDate"></li>
			<li>
				<select required name="period">
					<option ${plannedRecord.getDayPosition() == 0 ? "selected" : ""} value="0">без повторений</option>
					<option ${plannedRecord.getDayPosition() == 7 ? "selected" : ""} value="7">каждую неделю</option>
					<option ${plannedRecord.getDayPosition() == 30 ? "selected" : ""} value="30">каждый месяц</option>
				</select>
			</li>
			<input type="hidden" name="id" value="${plannedRecord.getId()}">
		</ul>
		<div class="push_text">
			<p>Заметки</p>
			<p><textarea rows="10" cols="45" name="text">${plannedRecord.getNote()}</textarea></p>
			<button name="clear_b" value="0">Удалить</button>
			<button name="repair_b" value="1">Изменить</button>
		</div>
	</section>
</form>
<footer class="foot"></footer>
</body>
</html>