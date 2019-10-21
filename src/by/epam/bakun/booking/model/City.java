package by.epam.bakun.booking.model;

import java.util.List;
import java.util.Objects;

public class City {
    private int countryId;
    private int cityId;
    private String cityName;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCountryId() {
        return 1;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getCountryId() == city.getCountryId() &&
                getCityId() == city.getCityId() &&
                getCityName().equals(city.getCityName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountryId(), getCityId(), getCityName());
    }

    @Override
    public String toString() {
        return "City{" +
                "countryId=" + countryId +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
