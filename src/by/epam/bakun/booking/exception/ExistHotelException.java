package by.epam.bakun.booking.exception;

public class ExistHotelException extends HotelException {
    public ExistHotelException(int roomId) {
        super("Room " + roomId + " already exist", roomId);
    }
}
