<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<header class="clearfix" id="header">
    <nav class="clearfix" id="hdmenu">
        <ul class= "menu">
            <li> <a href=" ">Контакты</a>
            <li> <a href=" ">Ставки</a>
            <li> <a href=" ">Личный кабинет</a>
        </ul>
    </nav>
</header>
<h1>ВХОД</h1>
<div align ="center" class = "formLogin">
    <form role="form" method="post" action="<c:url value="/login" /> ">
        <div class="buttonEmail">
            <input type="text" name="username"
                   placeholder="password" required="">
        </div>
        <div class="buttonPassword">
            <input type="password" name="password"
                   placeholder="password" required="">
        </div>
        <div class="buttonLogin"><input type="submit" placeholder="Войти" ></input></div>
    </form>
    <div class="buttonRegistration"><a href="<c:url value="/registration"/> "><input type="submit" value="Регистрация" required/></a> </div>
</div>
    <footer class = "clearfix" id = "footer">

        <div class="contacts">
            <div class="navigation">
                <nav class="navigation">
                    <ul class = "menu">
                        <li><a href=" ">Результаты</a></li>
                        <li><a href=" ">Мои ставки</a></li>
                        <li><a href=" ">Инструкция</a></li>
                    </ul>
                </nav>
            </div>
            <div class = "telephone">
                <img src="Image/Icons/iconphone.png"><br>
                <b>Телефон</b>
                <p>+375(29)000-000-000</p>
            </div>
            <div class="adress">
                <img src="Image/Icons/iconadress.png" ><br>
                <b>Адрес</b>
                <p>Гикало 9, Минск</p>
            </div>
            <div class = "email">
                <img src="Image/Icons/iconmap.png" ><br>
                <b>Email</b>
                <p>stavki@gmail.com</p>
            </div>
        </div>
        <div class="avtorfoot">
            <p>©2016 Stavki.by</p>
        </div>
    </footer>
</body>
</html>
