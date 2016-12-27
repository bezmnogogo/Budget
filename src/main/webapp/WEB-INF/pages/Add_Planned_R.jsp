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
		<h1> Добавление расходов</h1>
		<form method="post" action="<c:url value=" "/> ">
			<div class="sum"> 
				<p>Сумма</p>
				<input type="text" name="sum">
			</div>
			<div class="category">
				<p>Категории</p>
				<input type="text" name="category">
			</div>
			<div class="period">
				<p>Период повтора</p>
				<input type="text" name="period">
			</div>
			<div class="data">
				<p>Дата</p>
				<input type="text" name="data">
			</div>
			<div class="person_limit">
				<p>Заметки</p>
				<textarea rows="10" cols="45" name="text"></textarea>
			</div>
			<input type="submit" name="add_button" value="Добавить">
		</form>	
	</body>
</html>
