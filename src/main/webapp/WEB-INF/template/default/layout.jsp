<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><tiles:insertAttribute name="title"/></title>

    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap-theme.min.css" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap-theme.css" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.css" />"/>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>

</head>
<body>
<div class="container">
    <tiles:insertAttribute name="menu"/>
    <tiles:insertAttribute name="body"/>
</div>

</body>
</html>
