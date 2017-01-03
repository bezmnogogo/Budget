<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
	<head>
		<title>Категории</title>
	</head>
	<body>
		<section>
			<h2>Категории:</h2>
			<ul>
				<c:forEach var="category" items="${categories}">
					<li><a href="<c:url value="/categories/getRecords/${category.getId()}"/> ">${category.getType()}</a></li>
				</c:forEach>
			</ul>
			<a href="">Добавить +</a>
		</section>
		<section>
			<h2>Список расходов ${message}:</h2>
			<ul>
				<c:if test="${records != null}">
					<c:forEach var="record" items="${records}">
						<li>${record.toString()}</li>
					</c:forEach>
				</c:if>
			</ul>
		</section>
	</body>
</html>