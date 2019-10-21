package by.epam.bakun.booking.web.example;

import by.epam.bakun.booking.dao.HotelDAO;
import by.epam.bakun.booking.dao.RoomDAO;
import by.epam.bakun.booking.model.Hotel;
import by.epam.bakun.booking.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.bakun.booking.model.Room.Comfort.*;

@WebServlet("/hotel")
public class ServletController extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String pathJSP = "/jsp/list.jsp";
        String action = req.getParameter("action");
        String hotelIdString = req.getParameter("hotelId");
        HotelDAO hotelDAO = new HotelDAO();
        List<Hotel> list = hotelDAO.getAll();
        if (hotelIdString == null) {
            req.setAttribute("listStorage", list);
            req.getRequestDispatcher(pathJSP).forward(req, resp);
            return;
        }
        Hotel hotel;
        if (hotelIdString.equals("newHotel")) {
            hotel = new Hotel();
            String hotelName = req.getParameter("hotelName");
            int starRating = Integer.parseInt(req.getParameter("starRating"));
            int cityId = Integer.parseInt(req.getParameter("cityName"));
            hotel.setCityId(cityId);
            hotel.setHotelName(hotelName);
            hotel.setStarRating(starRating);
            hotelDAO.create(hotel);
            resp.sendRedirect("hotel");
            return;
        }
        int hotelId = Integer.parseInt(hotelIdString);
        hotel = hotelDAO.getById(hotelId);
        String roomIdString = req.getParameter("roomId");
        if (roomIdString == null) {
            req.setAttribute("hotel", hotel);
            req.getRequestDispatcher("/jsp/edit.jsp").forward(req, resp);
            return;
        }
        Room room;
        if (roomIdString.equals("newRoom")) {
            RoomDAO roomDAO = new RoomDAO();
            room = new Room();
            room.setHotelId(Integer.parseInt(req.getParameter("hotelId")));
            room.setCapacity(Integer.parseInt(req.getParameter("capacity")));
            room.setPrice(Double.parseDouble(req.getParameter("price")));
            int numberOfEnum = Integer.parseInt(req.getParameter("comfort"));
            switch (numberOfEnum) {
                case (1):
                    room.setComfort(STANDART);
                    break;
                case (2):
                    room.setComfort(SEMILUXURY);
                    break;
                case (3):
                    room.setComfort(LUXURY);
                    break;
                default:
                    break;
            }
            roomDAO.create(room);
            resp.sendRedirect("hotel");
            return;
        }


    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String pathJSP = "/jsp/list.jsp";
        String action = req.getParameter("action");
        HotelDAO hotelDAO = new HotelDAO();
        List<Hotel> list = hotelDAO.getAll();
        if (action == null) {
            req.setAttribute("listStorage", list);
            req.getRequestDispatcher(pathJSP).forward(req, resp);
            return;
        }
        int hotelId = Integer.parseInt(req.getParameter("hotelId"));
        Hotel hotel;
        switch (action) {
            case ("delete"):
                hotelDAO.delete(hotelId);
                resp.sendRedirect("hotel");
                return;
            case ("view"):
            case ("edit"):
                hotel = hotelDAO.getById(hotelId);
                break;
            default:
                throw new IllegalArgumentException("Action" + action + "is illegal");
        }
        req.setAttribute("hotel", hotel);
        req.getRequestDispatcher("view".equals(action)?"/jsp/view.jsp":"/jsp/edit.jsp").forward(req, resp);
    }
}
