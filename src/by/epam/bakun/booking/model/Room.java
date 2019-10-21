package by.epam.bakun.booking.model;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Room implements Comparable<Room> {
    private int roomId;
    private int capacity;
    private double price;
    private Comfort comfort;
    private int hotelId;
    private List<Booking> bookingList;

    public Room() {
        this(new Random().nextInt());
    }

    public Room(int roomId) {
        this.roomId = roomId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int gethotelId() {
        return hotelId;
    }

    public enum Comfort {
        STANDART("standart"),
        SEMILUXURY("semi-luxury"),
        LUXURY("luxury");

        private String comfortName;

        Comfort(String name) {
            this.comfortName = name;
        }
        public String getComfortName() {
            return comfortName;
        }
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Comfort getComfort() {
        return comfort;
    }

    public void setComfort(Comfort comfort) {
        this.comfort = comfort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomId == room.roomId &&
                capacity == room.capacity &&
                Double.compare(room.price, price) == 0 &&
                comfort == room.comfort;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, capacity, price, comfort);
    }

    @Override
    public String toString() {
        return "room{" +
                "roomId=" + roomId +
                ", capacity=" + capacity +
                ", price=" + price +
                ", comfort=" + comfort +
                '}';
    }

    @Override
    public int compareTo(Room o) {
        return Integer.compare(this.getRoomId(), o.getRoomId());
    }
}
