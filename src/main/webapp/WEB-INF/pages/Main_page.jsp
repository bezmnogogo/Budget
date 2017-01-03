
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
			<a href="">Главная</a>	
			<a href="">Обзор расходов</a>
			<a href="">Категории</a>
			<a href="">По времени</a>
			<a href="">Личный кабинет</a>
		</nav>
		<div class="last_expenses">
			<h2>Последние траты</h2>
			<ul>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
			<a href="" class="add_enemy">Добавить трату+</a>
		</div>
		<div class="near_expenses">
			<h2> Ближайшие запланированные расходы</h2>
			<ul>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
			<a href="" class="add_enemy">Добавить расход+</a>
		</div>
		<div class="information">
			<h2> Информация о картах</h2>
			<ul>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
			<a href="" class="add_enemy">Добавить карту+</a>
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