
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
	<head>
		<title>Обзор по времени</title>
	</head>
	<body>
	<nav>
		<a href="<c:url value="/main"/> ">Главная</a>
		<a href="<c:url value="/records/Overview/"/>">Обзор расходов</a>
		<a href="<c:url value="/categories/"/>">Категории</a>
		<a href="<c:url value="/records/mounthlyRecords"/>">По времени</a>
		<a href="<c:url value="/privateRoom/"/>">Личный кабинет</a>
	</nav>
	<div class="slider">
		<div class="arrow_left">
			<a href="<c:url value="/records/getRecordsByMounth/${prevMounth}"/> " name="arrow_left">предыдущий месяц</a>
		</div>
		<div class="mounths">
			<p>${mounth}</p>
		</div>
		<div class="arrow_right">
			<a href="<c:url value="/records/getRecordsByMounth/${nextMounth}"/>" name="arrow_right">следующий месяц</a>
		</div>
	</div>
		<p>
			<ul>
				<c:forEach var="record" items="${records}">
					<c:choose>
						<c:when test="${record.getPlanned() == true}">
							<li><a href="<c:url value="/records/getRecord/1/${record.getId()}"/> ">${record.toString()}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="<c:url value="/records/getRecord/0/${record.getId()}"/> ">${record.toString()}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
	        </ul>
		</p>
	</body>
</html>
