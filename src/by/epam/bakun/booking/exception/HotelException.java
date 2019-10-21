package by.epam.bakun.booking.exception;

public class HotelException extends RuntimeException {
    private final int roomId;

    public HotelException(String message, int roomId) {
        super(message);
        this.roomId = roomId;
    }

    public HotelException(Exception e) {
        this(e.getMessage(), 0);
    }

    public int getUuid() {
        return roomId;
    }
}
