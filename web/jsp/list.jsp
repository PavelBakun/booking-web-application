<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Hotels</title>
    <link rel="stylesheet" href="css/styleFormInput.css">
</head>
<body>
<jsp:include page="/jsp/fragments/header.jsp"/>
<div class="container">
    <table border="1" width="1200" cellpadding="10" cellspacing="0">
        <caption><b>Storage of Hotel</b></caption>
        <thead bgcolor="#deb887" title="Шапка">
        <tr>
            <th>HotelId</th>
            <th>HotelName</th>
            <th>StarRating</th>
            <th>CityId</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody align="center" bgcolor="#faebd7" title="List Of Room">
        <c:forEach items="${listStorage}" var="hotel">
            <jsp:useBean id="hotel" type="by.epam.bakun.booking.model.Hotel"/>
            <tr>
                <th>${hotel.hotelId}</th>
                <th>
                    <form class="nav_link" name="loginForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="viewHotel" />
                        <input type="hidden" name="hotelId" value="${hotel.hotelId}" />
                        <input type="submit" value="${hotel.hotelName}"/>
                    </form></th>
                <th>${hotel.starRating}</th>
                <th>${hotel.cityId}</th>
                <th><a href="hotel?hotelId=${hotel.hotelId}&action=delete">delete</a></th>
                <th><a href="hotel?hotelId=${hotel.hotelId}&action=edit">edit</a></th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form class="form1" action="hotel?hotelId=newHotel" method="post" name="formHotel">
        <div class="block_for_search">
            <div class="block">
                <label for="text">hotel id</label>
                <input id="text" type="text" value="${hotel.hotelId}" name="hotelId" readonly>
            </div>
            <div class="block">
                <label for="name">hotel name</label>
                <input type="text" id="name" name="hotelName" value="${hotel.hotelName}">
            </div>
            <div class="block">
                <label for="rating">star rating</label>
                <input type="number" id="rating" name="starRating" value="${hotel.starRating}">
            </div>
            <div class="block">
                <label for="city">city</label>
                <input type="text" id="city" name="cityName" value="${hotel.cityId}">
            </div>
            <div class="block">
                <label></label>
                <button type="submit">create new hotel</button>
            </div>
            <div class="block">
                <label></label>
                <button onclick="window.history.back()">back</button>
            </div>
        </div>
    </form>
    <div class="content">
        <jsp:include page="/jsp/fragments/footer.jsp"/>
</body>
</html>
