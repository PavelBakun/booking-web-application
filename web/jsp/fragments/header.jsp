<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="config.text" />
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
                <a class="nav_link" href="hotel"><fmt:message key="nav.link.home" /></a>
                <a class="nav_link" href="hotel"><fmt:message key="nav.link.country" /></a>
                <a class="nav_link" href="hotel"><fmt:message key="nav.link.hotels" /></a>
                <a class="nav_link" href="register.html"><fmt:message key="nav.link.register" /></a>
            </nav>
        </div>
    </div>
</header>