package by.epam.bakun.booking.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Booking {
    private int bookingId;
    private LocalDate start;
    private LocalDate finish;
    private boolean activeBook;
    private int roomId;
    private int hotelId;
    private int clientId;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getFinish() {
        return finish;
    }

    public void setFinish(LocalDate finish) {
        this.finish = finish;
    }

    public boolean isActiveBook() {
        return activeBook;
    }

    public void setActiveBook(boolean activeBook) {
        this.activeBook = activeBook;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return getBookingId() == booking.getBookingId() &&
                isActiveBook() == booking.isActiveBook() &&
                getRoomId() == booking.getRoomId() &&
                getHotelId() == booking.getHotelId() &&
                getClientId() == booking.getClientId() &&
                Objects.equals(getStart(), booking.getStart()) &&
                Objects.equals(getFinish(), booking.getFinish());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookingId(), getStart(), getFinish(), isActiveBook(), getRoomId(), getHotelId(), getClientId());
    }
}
