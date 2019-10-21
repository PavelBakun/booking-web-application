package by.epam.bakun.booking.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hotel {
    private int hotelId;
    private String hotelName;
    private int starRating;
    private String cityName;
    private List<Room> rooms = new ArrayList<>();
    private int cityId;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;
        Hotel hotel = (Hotel) o;
        return getHotelId() == hotel.getHotelId() &&
                getStarRating() == hotel.getStarRating() &&
                getCityId() == hotel.getCityId() &&
                Objects.equals(getHotelName(), hotel.getHotelName()) &&
                Objects.equals(cityName, hotel.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHotelId(), getHotelName(), getStarRating(), cityName, getCityId());
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", starRating=" + starRating +
                ", cityName='" + cityName + '\'' +
                ", rooms=" + rooms +
                ", cityId=" + cityId +
                '}';
    }
}
