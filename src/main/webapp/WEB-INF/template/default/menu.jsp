<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- Static navbar -->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Budget+</a>
        </div>
        <div id="menu" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">

                <li id="menu-mixingRecipes"><a href="<c:url value="/login"/>">login</a></li>
                <li id="menu-extruderRecipes"><a href="<c:url value="/registration" />">registration</a></li>
                <c:if test="${user != null}">
                <li id="menu-archive"><a href="<c:url value="/main"/>">домой</a></li>
                <li id="menu-logout"><a href="<c:url value="/logout"/>">
                    Выйти${user.getUsername()} <span style="color:blue">
                        </span></a>
                </li>
                </c:if>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
    <!--/.container-fluid -->
</nav>
