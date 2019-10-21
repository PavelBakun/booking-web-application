<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:useBean id="hotel" type="by.epam.bakun.booking.model.Hotel" scope="request"/>
    <title>Hotel ${hotel.hotelName}</title>
    <link rel="stylesheet" href="css/styleFormInput.css">
</head>
<body>
    <jsp:include page="/jsp/fragments/header.jsp"/>
    <div class="container">
        <table border="1" width="1200" cellpadding="10" cellspacing="0">
            <caption><b>Hotel ${hotel.hotelName}&nbsp;<a href="hotel?hotelId=${hotel.hotelId}&action=edit">edit</a></b></caption>
            <thead bgcolor="#deb887" title="Шапка">
            <tr>
                <th>RoomId</th>
                <th>Capacity</th>
                <th>Price</th>
                <th>Comfort</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody align="center" bgcolor="#faebd7" title="List Of Room">
                <tr>
                    <th>HotelId=${hotel.hotelId}</th>
                    <th>HotelName=<a href="hotel?hotelId=${hotel.hotelId}&action=view">${hotel.hotelName}</a></th>
                    <th>StarRating=${hotel.starRating}</th>
                    <th>CityId=${hotel.cityId}</th>
                    <th><a href="hotel?hotelId=${hotel.hotelId}&action=delete">delete</a></th>
                    <th><a href="hotel?hotelId=${hotel.hotelId}&action=edit">edit</a></th>
                </tr>
                <c:forEach items="${hotel.rooms}" var = "room">
                    <jsp:useBean id="room" type="by.epam.bakun.booking.model.Room"/>
                    <tr>
                        <th>RoomId=${room.roomId}</th>
                        <td>Capacity=${room.capacity}</td>
                        <td>Price=${room.price}</td>
                        <td>Comfort=${room.comfort}</td>
                        <td><a href="hotel?roomId=${room.roomId}&action=delete">delete</a></td>
                        <td></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form class="form1" action="hotel?hotelId=${hotel.hotelId}&roomId=newRoom" method="post" name="formRoom">
            <div class="block_for_search">
                <div class="block">
                    <label for="textRoomId">room Id</label>
                    <input id="textRoomId" type="text" value="${room.roomId}" name="roomId" readonly>
                </div>
                <div class="block">
                    <label for="roomCapacity">capacity</label>
                    <select name="capacity" id="roomCapacity">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                    </select>
                </div>
                <div class="block">
                    <label for="roomPrice">price</label>
                    <input type="number" id="roomPrice" name="price" value="${room.price}">
                </div>
                <div class="block">
                    <label for="roomComfort">comfort</label>
                    <select name="comfort" id="roomComfort">
                        <option value="1">standart</option>
                        <option value="2">semi-luxury</option>
                        <option value="3">luxury</option>
                    </select>
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
    </div>
    <div class="contact">
    <jsp:include page="/jsp/fragments/footer.jsp"/>
    </div>
</body>
</html>