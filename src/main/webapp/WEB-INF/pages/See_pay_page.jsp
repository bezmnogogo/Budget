
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
		<form method="post" action="<c:url value="/records/changeRecord/ "/> ">
		<header>
			<nav>
				<a href="<c:url value="/main"/> ">Главная</a>
				<a href="<c:url value="/records/Overview/"/>">Обзор расходов</a>
				<a href="<c:url value="/categories/"/>">Категории</a>
				<a href="<c:url value="/records/mounthlyRecords"/>">По времени</a>
				<a href="<c:url value="/privateRoom/"/>">Личный кабинет</a>
				<a href="<c:url value="/logout/"/>">Выйти(${user.getUsername()})</a>
			</nav>
		</header>
		<section>

			<ul>
				<li>Расход</li>
				<li>Сумма</li>
				<li>Категории</li>
				<li>Дата траты</li>
				<li>Карта</li>
				<li>Заметка</li>
			</ul>
			<ul>
				<li><textarea rows="1" cols="20" name="${record.getId()}">Завершенный</textarea> ${message}</li>
				<li><textarea rows="1" cols="20" name="sum">${record.getSum()}</textarea></li>
				<li>
					<select required name="selectedCategory">
						<c:forEach var="category" items="${categories}">
							<c:choose>
								<c:when test="${category.getType() == record.getCategory().getType()}">
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
								<c:when test="${record.getCard().getCardNumber() == null && card.getCardNumber() != record.getCard().getCardNumber()}">
									<option selected disabled>выберите карту</option>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${card.getCardNumber() == record.getCard().getCardNumber()}">
									<option selected value="${card.getCardNumber()}">${card.getCardNumber()}</option>
								</c:when>
								<c:otherwise>
									<option value="${card.getCardNumber()}">${card.getCardNumber()}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</li>
				<li><input type="date" value="${record.getRecordDate()}"  name="recordDate"></li>
				<input type="hidden" name="id" value="${record.getId()}">
			</ul>
			<p>Заметки</p>
		    <p><textarea rows="10" cols="45" name="text">${record.getNote()}</textarea></p>
			<button name="clear_b" value="0">Удалить</button>
			<button name="repair_b" value="1">Изменить</button>
		</section>
		</form>
	</body>
</html>