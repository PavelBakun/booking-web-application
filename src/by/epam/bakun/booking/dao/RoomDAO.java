package by.epam.bakun.booking.dao;

import by.epam.bakun.booking.exception.DAOException;
import by.epam.bakun.booking.model.Room;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO implements DAO<Room, Integer> {

    private static Logger logger = Logger.getLogger(RoomDAO.class.getName());
    private static final String CREATE_ROOM_QUERY =
            "INSERT INTO ROOMS (room_capacity, room_price, comf_name, hotels_hotel_id) VALUES (?,?,?,?)";
    private static final String GET_ROOM_BY_ID_QUERY = "SELECT * FROM rooms WHERE room_id=?";
    private static final String GET_ALL_ROOM_BY_HOTELID_QUERY = "SELECT * FROM rooms WHERE hotels_hotel_id=?";
    private static final String GET_ALL_ROOM_QUERY = "SELECT * FROM rooms";
    private static final String UPDATE_ROOM_QUERY =
            "UPDATE rooms SET (room_capacity, room_price, comf_name) VALUES (?,?,?) WHERE room_id=?";
    private static final String DELETE_ROOM_QUERY = "DELETE FROM rooms WHERE room_id=?";

    @Override
    public void create(Room room) throws DAOException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(CREATE_ROOM_QUERY)) {
            statement.setInt(1, room.getCapacity());
            statement.setDouble(2, room.getPrice());
            statement.setString(3, room.getComfort().getComfortName());
            statement.setInt(4, room.gethotelId());
            statement.execute();
        } catch (SQLException e) {
            logger.error("Create Room exception" + e);
            throw new DAOException("Create Room exception roomId = " + room.getRoomId(), room.getRoomId());
        }
    }

    @Override
    public Room getById(Integer roomId) throws DAOException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_ROOM_BY_ID_QUERY)) {
            statement.setInt(1, roomId);
            try (ResultSet resultSetRoom = statement.executeQuery()) {
                if (resultSetRoom.next()) {
                    return setRoom(resultSetRoom);
                }
                resultSetRoom.close();
                statement.close();
                con.close();
            }
        } catch (SQLException e) {
            logger.error("GetById Room exception" + e);
            throw new DAOException("GetById Room exception roomId = " + roomId, roomId);
        }
        return null;
    }

    public List<Room> getAll(Integer hotelId) throws DAOException {
        List<Room> rooms = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_ALL_ROOM_BY_HOTELID_QUERY)) {
            statement.setInt(1, hotelId);
            try (ResultSet resultSetRoom = statement.executeQuery()) {
                while (resultSetRoom.next()) {
                    rooms.add(setRoom(resultSetRoom));
                    logger.debug("Get All Room By hotel Id = " + hotelId);
                }
                resultSetRoom.close();
                statement.close();
                con.close();
            }
        } catch (SQLException e) {
            logger.error("GetAllRoom by hotelId exception" + e);
            throw new DAOException("GetAllRoom by hotelId exception, hotelId=" + hotelId, hotelId);
        }
        return rooms;
    }

    @Override
    public List<Room> getAll() throws DAOException {
        List<Room> rooms = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_ALL_ROOM_QUERY);
             ResultSet resultSetRoom = statement.executeQuery()) {
            while (resultSetRoom.next()) {
                rooms.add(setRoom(resultSetRoom));
            }
        } catch (SQLException e) {
            logger.error("GetAllRoom exception" + e);
            throw new DAOException("GetAllRoom exception", 0);
        }
        return rooms;
    }

    @Override
    public void update(Room room) {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(UPDATE_ROOM_QUERY)) {
            statement.setInt(4, room.getRoomId());
            statement.setInt(1, room.getCapacity());
            statement.setDouble(2, room.getPrice());
            statement.setString(3, room.getComfort().getComfortName());
            statement.execute();
        } catch (SQLException e) {
            logger.error("Update Room exception" + e);
            throw new DAOException("Update Room exception roomId = " + room.getRoomId(), room.getRoomId());
        }
    }

    @Override
    public void delete(Integer roomId) {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(DELETE_ROOM_QUERY)) {
            statement.setInt(1, roomId);
            statement.execute();
        } catch (SQLException e) {
            logger.error("Delete Room exception" + e);
            throw new DAOException("Delete Room exception roomId = " + roomId, roomId);
        }
    }

    private Room setRoom(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setRoomId(resultSet.getInt("room_id"));
        room.setHotelId(resultSet.getInt("hotels_hotel_id"));
        room.setCapacity(resultSet.getInt("room_capacity"));
        room.setPrice(resultSet.getDouble("room_price"));
        String comfortName = resultSet.getString("comf_name");
        if (comfortName.equals("semi-luxury")) {
            comfortName = "semiluxury";
        }
        room.setComfort(Room.Comfort.valueOf(comfortName.toUpperCase()));
        return room;
    }

}
