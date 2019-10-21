package by.epam.bakun.booking.dao;

import by.epam.bakun.booking.exception.DAOException;
import by.epam.bakun.booking.model.Country;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO implements DAO<Country, Integer> {

    private static Logger logger = Logger.getLogger(CountryDAO.class.getName());
    private static final String CREATE_COUNTRY_QUERY =
            "INSERT INTO country (country_name) VALUES (?)";
    private static final String GET_COUNTRY_BY_ID_QUERY =
            "SELECT * FROM country WHERE country_id=?";
    private static final String GET_ALL_CITY_QUERY =
            "SELECT * FROM city JOIN country c3 on country_country_id = c3.country_id order by city.city_id";
    private static final String UPDATE_COUNTRY_QUERY =
            "UPDATE country SET (country_name) VALUES (?) WHERE country_id=?";
    private static final String DELETE_COUNTRY_QUERY = "DELETE FROM country WHERE country_id=?";

    @Override
    public void create(Country country) throws DAOException {
        try (PreparedStatement statement = getStatement(CREATE_COUNTRY_QUERY)) {
            statement.setString(1, country.getCountryName());
            logger.debug("Create Country " + country.getCountryName());
            statement.execute();
        } catch (SQLException e) {
            logger.error("Create Country exception" + e);
            throw new DAOException("Create Country exception cityId = " + country.getCountryName(), country.getCountryId());
        }
    }

    @Override
    public Country getById(Integer countryId) throws DAOException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_COUNTRY_BY_ID_QUERY)) {
            statement.setInt(1, countryId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    logger.debug("getById Country =" + countryId);
                    return setCountry(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("GetById Country exception" + e);
            throw new DAOException("GetById Country exception country = " + countryId, countryId);
        }
        return null;
    }

    @Override
    public List<Country> getAll() throws DAOException {
        List<Country> countries = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_ALL_CITY_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                countries.add(setCountry(resultSet));
                logger.debug("Get All Country");
            }
            resultSet.close();
            statement.close();
            con.close();
            return countries;
        } catch (SQLException e) {
            logger.error("GetAll Country exception" + e);
            throw new DAOException("GetAll Country exception hotelId", 0);
        }
    }

    @Override
    public void update(Country city) throws DAOException {
        try (PreparedStatement statement = getStatement(UPDATE_COUNTRY_QUERY)) {
            statement.setInt(2, city.getCountryId());
            statement.setString(1, city.getCountryName());
            statement.execute();
            logger.debug("Update Country By cityId = " + city.getCountryName());
        } catch (SQLException e) {
            logger.error("Update Country exception" + e);
            throw new DAOException("Update Country exception cityId = " + city.getCountryId(), city.getCountryId());
        }
    }

    @Override
    public void delete(Integer countryId) {
        try (PreparedStatement statement = getStatement(DELETE_COUNTRY_QUERY)) {
            statement.setInt(1, countryId);
            statement.execute();
            logger.debug("Delete Country By country Id = " + countryId);
        } catch (SQLException e) {
            logger.error("Delete Country exception" + e);
            throw new DAOException("Delete Country exception countryId = " + countryId, countryId);
        }
    }

    private Country setCountry(ResultSet resultSet) throws SQLException {
        Country country = new Country();
        country.setCountryId(resultSet.getInt("country_id"));
        country.setCountryName(resultSet.getString("country_name"));
        return country;
    }

    private PreparedStatement getStatement(String query) throws SQLException {
        Connection con = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = con.prepareStatement(query);
        return statement;
    }
}
