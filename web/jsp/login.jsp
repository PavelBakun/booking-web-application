<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="config.text"/>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/styleFormInput.css">
</head>
<header class="header">
    <div class="container">
        <div class="header_inner">
            <div class="header_logo">Booking24.by</div>
            <nav class="nav">
                <form class="nav_link">
                    <select id="language" name="language" onchange="submit()">
                        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                    </select>
                </form>
                <a class="nav_link" href="register.html"><fmt:message key="nav.link.register"/></a>
            </nav>
        </div>
    </div>
</header>
<body>
<div class="container">
    <form name="loginForm" method="POST" action="controller">
        <input type="hidden" name="command" value="login"/>
        Login:<br/>
        <input type="text" name="login" value=""/>
        <br/>Password:<br/>
        <input type="password" name="password" value=""/>
        <br/>
        ${errorLoginPassMessage}
        <br/>
        ${wrongAction}
        <br/>
        ${nullPage}
        <br/>
        <input type="submit" value="Log in"/>
    </form>
    <hr/>
</div>
<jsp:include page="/jsp/fragments/footer.jsp"/>
</body>
</html>
