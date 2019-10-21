package by.epam.bakun.booking.model;

import java.util.List;
import java.util.Objects;

public class Country {
    private int countryId;
    private String countryName;
    private List<City> cityList;

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country country = (Country) o;
        return getCountryId() == country.getCountryId() &&
                getCountryName().equals(country.getCountryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountryId(), getCountryName());
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                ", cityList=" + cityList +
                '}';
    }
}
