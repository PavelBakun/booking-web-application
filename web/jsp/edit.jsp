<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:useBean id="hotel" type="by.epam.bakun.booking.model.Hotel" scope="request"/>
    <title>Hotel ${hotel.hotelName} edit</title>
    <link rel="stylesheet" href="css/styleFormInput.css">
</head>
<body>
<jsp:include page="/jsp/fragments/header.jsp"/>
<div class="container">
        <h2 class="intro_undertitle">Hotel ${hotel.hotelName}&nbsp;<a href="hotel?hotelId=${hotel.hotelId}&action=edit">edit</a></h2>
        <form class="form1" action="hotel" method="post" name="formHotel">
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
                    <button type="submit">save</button>
                </div>
                <div class="block">
                    <label></label>
                    <button onclick="window.history.back()">back</button>
                </div>
            </div>
        </form>
        <c:forEach items="${hotel.rooms}" var = "room">
            <jsp:useBean id="room" type="by.epam.bakun.booking.model.Room"/>
            <form class="form1" action="room" method="post" name="formRoom">
                <div class="block_for_search">
                    <div class="block">
                        <label for="textRoomId">room Id</label>
                        <input id="textRoomId" type="text" value="${room.roomId}" name="roomId" readonly>
                    </div>
                    <div class="block">
                        <label for="roomCapacity">capacity</label>
                        <input type="number" id="roomCapacity" name="capacity" value="${room.capacity}">
                    </div>
                    <div class="block">
                        <label for="roomPrice">price</label>
                        <input type="number" id="roomPrice" name="price" value="${room.price}">
                    </div>
                    <div class="block">
                        <label for="roomComfort">comfort</label>
                        <input type="text" id="roomComfort" name="comfort" value="${room.comfort}">
                    </div>
                    <div class="block">
                        <label></label>
                        <button type="submit">save</button>
                    </div>
                    <div class="block">
                        <label></label>
                        <button onclick="window.history.back()">back</button>
                    </div>
                </div>
            </form>
        </c:forEach>
</div>
        <jsp:include page="/jsp/fragments/footer.jsp"/>
</body>
</html>