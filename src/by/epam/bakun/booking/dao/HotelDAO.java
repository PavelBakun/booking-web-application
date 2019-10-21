package by.epam.bakun.booking.dao;

import by.epam.bakun.booking.exception.DAOException;
import by.epam.bakun.booking.model.Hotel;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO implements DAO<Hotel, Integer> {

    private static Logger logger = Logger.getLogger(HotelDAO.class.getName());
    private static final String CREATE_HOTEL_QUERY =
            "INSERT INTO hotels (hotel_name, hotel_star_rating, City_city_id) VALUES (?,?,?)";
    private static final String GET_HOTEL_BY_ID_QUERY =
            "SELECT * FROM hotels INNER JOIN city c2 on hotels.City_city_id = c2.city_id WHERE hotels.hotel_id=?";
    private static final String GET_ALL_HOTEL_BY_CITYID_QUERY =
            "SELECT * FROM hotels JOIN city c2 on hotels.City_city_id = c2.city_id WHERE City_city_id=?";
    private static final String GET_ALL_HOTEL_QUERY =
            "SELECT * FROM hotels JOIN city c2 on hotels.City_city_id = c2.city_id order by hotels.hotel_id";
    private static final String UPDATE_HOTEL_QUERY =
            "UPDATE hotels SET (hotel_name, hotel_star_rating) VALUES (?,?) WHERE hotel_id=?";
    private static final String DELETE_HOTEL_QUERY = "DELETE FROM hotels WHERE hotel_id=?";

    @Override
    public void create(Hotel hotel) throws DAOException {
        try (PreparedStatement statement = getStatement(CREATE_HOTEL_QUERY)) {
            statement.setString(1, hotel.getHotelName());
            statement.setInt(2, hotel.getStarRating());
            statement.setInt(3, hotel.getCityId());
            logger.debug("Create Hotel " + hotel.getHotelName());
            statement.execute();
        } catch (SQLException e) {
            logger.error("Create Hotel exception" + e);
            throw new DAOException("Create Hotel exception hotelId = " + hotel.getHotelId(), hotel.getHotelId());
        }
    }

    @Override
    public Hotel getById(Integer hotelId) throws DAOException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_HOTEL_BY_ID_QUERY)) {
            statement.setInt(1, hotelId);
            try (ResultSet resultSet = statement.executeQuery()) {
                Hotel hotel = new Hotel();
                if (resultSet.next()) {
                    hotel = setHotel(resultSet);
                    logger.debug("getById Hotel " + hotelId);
                }
                RoomDAO roomDAO = new RoomDAO();
                hotel.setRooms(roomDAO.getAll(hotelId));
                return hotel;
            }
        } catch (SQLException e) {
            logger.error("GetById Hotel exception" + e);
            throw new DAOException("GetById Hotel exception hotelId = " + hotelId, hotelId);
        }
    }

    public List<Hotel> getAll(Integer cityId) throws DAOException {
        List<Hotel> hotels = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_ALL_HOTEL_BY_CITYID_QUERY)) {
            statement.setInt(1, cityId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    hotels.add(setHotel(resultSet));
                    logger.debug("Get All Hotel By City Id = " + cityId);
                }
                resultSet.close();
                statement.close();
                con.close();
                for (Hotel hotel : hotels) {
                    RoomDAO roomDAO = new RoomDAO();
                    hotel.setRooms(roomDAO.getAll(hotel.getHotelId()));
                }
                return hotels;
            }
        } catch (SQLException e) {
            logger.error("GetByCityId Hotel exception" + e);
            throw new DAOException("GetByCityId Hotel exception hotelId", cityId);
        }
    }

    @Override
    public List<Hotel> getAll() throws DAOException {
        List<Hotel> hotels = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_ALL_HOTEL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                hotels.add(setHotel(resultSet));
                logger.debug("Get All Hotel");
            }
            resultSet.close();
            statement.close();
            con.close();
            for (Hotel hotel : hotels) {
                RoomDAO roomDAO = new RoomDAO();
                hotel.setRooms(roomDAO.getAll(hotel.getHotelId()));
            }
            return hotels;
        } catch (SQLException e) {
            logger.error("GetAll Hotel exception" + e);
            throw new DAOException("GetAll Hotel exception hotelId", 0);
        }
    }

    @Override
    public void update(Hotel hotel) throws DAOException {
        try (PreparedStatement statement = getStatement(UPDATE_HOTEL_QUERY)) {
            statement.setInt(3, hotel.getHotelId());
            statement.setString(1, hotel.getHotelName());
            statement.setInt(2, hotel.getStarRating());
            statement.execute();
            logger.debug("Update Hotel By hotel Id = " + hotel.getHotelName());
        } catch (SQLException e) {
            logger.error("Update Hotel exception" + e);
            throw new DAOException("Update Hotel exception hotel = " + hotel.getHotelId(), hotel.getHotelId());
        }
    }

    @Override
    public void delete(Integer hotelId) {
        try (PreparedStatement statement = getStatement(DELETE_HOTEL_QUERY)) {
            statement.setInt(1, hotelId);
            statement.execute();
            logger.debug("Delete Hotel By hotel Id = " + hotelId);
        } catch (SQLException e) {
            logger.error("Delete Hotel exception" + e);
            throw new DAOException("Delete Hotel exception hotelId = " + hotelId, hotelId);
        }
    }

    private Hotel setHotel(ResultSet resultSet) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setHotelId(resultSet.getInt("hotel_id"));
        hotel.setHotelName(resultSet.getString("hotel_name"));
        hotel.setStarRating(resultSet.getInt("hotel_star_rating"));
        hotel.setCityId(resultSet.getInt("c2.city_id"));
        return hotel;
    }

    private PreparedStatement getStatement(String query) throws SQLException {
        Connection con = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = con.prepareStatement(query);
        return statement;
    }
}
