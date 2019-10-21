package by.epam.bakun.booking.exception;

public class NotExistHotelException extends HotelException {
    public NotExistHotelException(int roomId) {
        super("Room " + roomId + " not exist", roomId);
    }
}
