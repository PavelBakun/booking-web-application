package by.epam.bakun.booking.dao;

import by.epam.bakun.booking.exception.DAOException;
import by.epam.bakun.booking.model.Booking;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO implements DAO<Booking, Integer> {

    private static Logger logger = Logger.getLogger(BookingDAO.class.getName());
    private static final String CREATE_BOOKING_QUERY =
            "INSERT INTO booking (booking_start,booking_finish, booking_is_active, rooms_room_id, rooms_hotels_hotel_id,"
                    + "clients_client_id) VALUES (?,?,?,?,?,?)";
    private static final String GET_BOOKING_BY_ID_QUERY =
            "SELECT * FROM booking WHERE booking.booking_id=?";
    private static final String GET_ALL_BOOKING_QUERY =
            "SELECT * FROM booking";
    private static final String UPDATE_BOOKING_QUERY =
            "UPDATE booking SET (booking_start,booking_finish, booking_is_active, rooms_room_id, rooms_hotels_hotel_id,"
                    + "clients_client_id) VALUES (?,?) WHERE booking_id=?";
    private static final String DELETE_BOOKING_QUERY = "DELETE FROM booking WHERE booking_id=?";

    @Override
    public void create(Booking booking) throws DAOException {
        try (PreparedStatement statement = getStatement(CREATE_BOOKING_QUERY)) {
            statement.setObject(1, booking.getStart());
            statement.setObject(2, booking.getFinish());
            statement.setInt(3, booking.isActiveBook() ? 1 : 0);
            statement.setInt(4, booking.getRoomId());
            statement.setInt(5, booking.getHotelId());
            statement.setInt(6, booking.getClientId());
            logger.debug("Create booking " + booking.getBookingId());
            statement.execute();
        } catch (SQLException e) {
            logger.error("Create booking exception" + e);
            throw new DAOException("Create booking exception BookingId = " + booking.getBookingId(), booking.getBookingId());
        }
    }

    @Override
    public Booking getById(Integer bookingId) throws DAOException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_BOOKING_BY_ID_QUERY)) {
            statement.setInt(1, bookingId);
            try (ResultSet resultSet = statement.executeQuery()) {
                Booking booking = new Booking();
                if (resultSet.next()) {
                    booking = setBooking(resultSet);
                    logger.debug("getById booking " + bookingId);
                }
                return booking;
            }
        } catch (SQLException e) {
            logger.error("GetById booking exception" + e);
            throw new DAOException("GetById booking exception bookingId = " + bookingId, bookingId);
        }
    }

    @Override
    public List<Booking> getAll() throws DAOException {
        List<Booking> bookings = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_ALL_BOOKING_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                bookings.add(setBooking(resultSet));
                logger.debug("Get All Booking");
            }
            resultSet.close();
            statement.close();
            con.close();
            return bookings;
        } catch (SQLException e) {
            logger.error("GetAll Booking exception" + e);
            throw new DAOException("GetAll Booking exception BookingId", 0);
        }
    }

    @Override
    public void update(Booking booking) throws DAOException {
        try (PreparedStatement statement = getStatement(UPDATE_BOOKING_QUERY)) {
            statement.setInt(7, booking.getBookingId());
            statement.setObject(1, booking.getStart());
            statement.setObject(2, booking.getFinish());
            statement.setInt(3, booking.isActiveBook() ? 1 : 0);
            statement.setInt(4, booking.getRoomId());
            statement.setInt(5, booking.getHotelId());
            statement.setInt(6, booking.getClientId());
            statement.execute();
            logger.debug("Update Booking By booking Id = " + booking.getBookingId());
        } catch (SQLException e) {
            logger.error("Update Booking exception" + e);
            throw new DAOException("Update Booking exception booking = " + booking.getBookingId(), booking.getBookingId());
        }
    }

    @Override
    public void delete(Integer bookingId) throws DAOException {
        try (PreparedStatement statement = getStatement(DELETE_BOOKING_QUERY)) {
            statement.setInt(1, bookingId);
            statement.execute();
            logger.debug("Delete Booking By booking Id = " + bookingId);
        } catch (SQLException e) {
            logger.error("Delete Booking exception" + e);
            throw new DAOException("Delete Booking exception bookingId = " + bookingId, bookingId);
        }
    }

    private Booking setBooking(ResultSet resultSet) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingId(resultSet.getInt("booking_id"));
        booking.setStart((LocalDate) resultSet.getObject("booking_start"));
        booking.setFinish((LocalDate) resultSet.getObject("booking_finish"));
        booking.setRoomId(resultSet.getInt("rooms_room_id"));
        booking.setHotelId(resultSet.getInt("rooms_hotels_hotel_id"));
        booking.setClientId(resultSet.getInt("clients_client_id"));
        return booking;
    }

    private PreparedStatement getStatement(String query) throws SQLException {
        Connection con = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = con.prepareStatement(query);
        return statement;
    }
}
