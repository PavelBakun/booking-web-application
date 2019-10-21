package by.epam.bakun.booking.dao;

import by.epam.bakun.booking.exception.DAOException;
import by.epam.bakun.booking.model.City;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAO implements DAO<City, Integer> {

    private static Logger logger = Logger.getLogger(CityDAO.class.getName());
    private static final String CREATE_CITY_QUERY =
            "INSERT INTO city (city_name, Country_city_id) VALUES (?,?)";
    private static final String GET_CITY_BY_ID_QUERY =
            "SELECT * FROM city INNER JOIN country c3 on country_country_id = c3.country_id WHERE city_id=?";
    private static final String GET_ALL_CITY_BY_COUNTRID_QUERY =
            "SELECT * FROM city JOIN country c3 on country_country_id = c3.country_id WHERE country_country_id=?";
    private static final String GET_ALL_CITY_QUERY =
            "SELECT * FROM city JOIN country c3 on country_country_id = c3.country_id order by city.city_id";
    private static final String UPDATE_CITY_QUERY =
            "UPDATE city SET (city_name, country_country_id) VALUES (?,?) WHERE city_id=?";
    private static final String DELETE_CITY_QUERY = "DELETE FROM city WHERE city_id=?";

    @Override
    public void create(City city) throws DAOException {
        try (PreparedStatement statement = getStatement(CREATE_CITY_QUERY)) {
            statement.setString(1, city.getCityName());
            statement.setInt(2, city.getCountryId());
            logger.debug("Create City " + city.getCityName());
            statement.execute();
        } catch (SQLException e) {
            logger.error("Create City exception" + e);
            throw new DAOException("Create City exception cityId = " + city.getCityId(), city.getCityId());
        }
    }

    @Override
    public City getById(Integer cityId) throws DAOException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_CITY_BY_ID_QUERY)) {
            statement.setInt(1, cityId);
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    logger.debug("getById City =" + cityId);
                    return setCity(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("GetById City exception" + e);
            throw new DAOException("GetById City exception hotelId = " + cityId, cityId);
        }
        return null;
    }

    public List<City> getAll(Integer countryId) throws DAOException {
        List<City> cities = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_ALL_CITY_BY_COUNTRID_QUERY)) {
            statement.setInt(1, countryId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(setCity(resultSet));
                    logger.debug("Get All City By Country Id = " + countryId);
                }
                resultSet.close();
                statement.close();
                con.close();
                return cities;
            }
        } catch (SQLException e) {
            logger.error("Get All City By CountryId  exception" + e);
            throw new DAOException("Get All City By CountryId  exception", countryId);
        }
    }

    @Override
    public List<City> getAll() throws DAOException {
        List<City> cities = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_ALL_CITY_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                cities.add(setCity(resultSet));
                logger.debug("Get All City");
            }
            resultSet.close();
            statement.close();
            con.close();
            return cities;
        } catch (SQLException e) {
            logger.error("GetAll City exception" + e);
            throw new DAOException("GetAll City exception hotelId", 0);
        }
    }

    @Override
    public void update(City city) throws DAOException {
        try (PreparedStatement statement = getStatement(UPDATE_CITY_QUERY)) {
            statement.setInt(3, city.getCityId());
            statement.setString(1, city.getCityName());
            statement.setInt(2, city.getCountryId());
            statement.execute();
            logger.debug("Update City By cityId = " + city.getCityName());
        } catch (SQLException e) {
            logger.error("Update City exception" + e);
            throw new DAOException("Update City exception cityId = " + city.getCityId(), city.getCityId());
        }
    }

    @Override
    public void delete(Integer cityId) {
        try (PreparedStatement statement = getStatement(DELETE_CITY_QUERY)) {
            statement.setInt(1, cityId);
            statement.execute();
            logger.debug("Delete City By hotel Id = " + cityId);
        } catch (SQLException e) {
            logger.error("Delete City exception" + e);
            throw new DAOException("Delete City exception hotelId = " + cityId, cityId);
        }
    }

    private City setCity(ResultSet resultSet) throws SQLException {
        City city = new City();
        city.setCityId(resultSet.getInt("city_id"));
        city.setCityName(resultSet.getString("city_name"));
        city.setCountryId(resultSet.getInt("c3.country_id"));
        return city;
    }

    private PreparedStatement getStatement(String query) throws SQLException {
        Connection con = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = con.prepareStatement(query);
        return statement;
    }
}
