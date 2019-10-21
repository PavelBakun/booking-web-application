package by.epam.bakun.booking.dao;

import by.epam.bakun.booking.exception.DAOException;
import by.epam.bakun.booking.model.Client;
import by.epam.bakun.booking.model.Country;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements DAO<Client, Integer> {

    private static Logger logger = Logger.getLogger(ClientDAO.class.getName());
    private static final String CREATE_CLIENT_QUERY =
            "INSERT INTO clients (client_login, client_password, client_mail) VALUES (?,?,?)";
    private static final String GET_CLIENT_BY_ID_QUERY =
            "SELECT * FROM clients WHERE client_id=?";
    private static final String GET_CLIENT_BY_LOGIN_QUERY =
            "SELECT * FROM clients WHERE client_login=?";
    private static final String GET_ALL_CLIENT_QUERY =
            "SELECT * FROM clients order by client_id";
    private static final String UPDATE_CLIENT_QUERY =
            "UPDATE clients SET (client_login, client_password, client_mail) VALUES (?,?,?) WHERE client_id=?";
    private static final String DELETE_CLIENT_QUERY = "DELETE FROM client WHERE client_id=?";

    @Override
    public void create(Client client) throws DAOException {
        try (PreparedStatement statement = getStatement(CREATE_CLIENT_QUERY)) {
            statement.setString(1, client.getLogin());
            statement.setString(2, client.getMySQLpassword());
            statement.setString(3, client.getMail());
            logger.debug("Create Client " + client.getLogin());
            statement.execute();
        } catch (SQLException e) {
            logger.error("Create Client exception" + e);
            throw new DAOException("Create Client exception clientLogin = " + client.getLogin(), client.getClientId());
        }
    }

    @Override
    public Client getById(Integer clientId) throws DAOException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_CLIENT_BY_ID_QUERY)) {
            statement.setInt(1, clientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    logger.debug("getById Client =" + clientId);
                    return setClient(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("GetById Client exception" + e);
            throw new DAOException("GetById Client exception client = " + clientId, clientId);
        }
        return null;
    }

    public Client getByLogin(String login) throws DAOException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_CLIENT_BY_LOGIN_QUERY)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    logger.debug("getByLogin Client =" + login);
                    return setClient(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("GetByLogin Client exception" + e);
            throw new DAOException("RegistrationException = " + login, 0);
        }
        return null;
    }

    @Override
    public List<Client> getAll() throws DAOException {
        List<Client> clients = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_ALL_CLIENT_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                clients.add(setClient(resultSet));
                logger.debug("Get All Client");
            }
            resultSet.close();
            statement.close();
            con.close();
            return clients;
        } catch (SQLException e) {
            logger.error("GetAll Client exception" + e);
            throw new DAOException("GetAll Client exception", 0);
        }
    }

    @Override
    public void update(Client client) throws DAOException {
        try (PreparedStatement statement = getStatement(UPDATE_CLIENT_QUERY)) {
            statement.setInt(4, client.getClientId());
            statement.setString(1, client.getLogin());
            statement.setString(2, client.getMySQLpassword());
            statement.setString(3, client.getMail());
            statement.execute();
            logger.debug("Update Client = " + client.getLogin());
        } catch (SQLException e) {
            logger.error("Update Client exception" + e);
            throw new DAOException("Update Client exception clientId = " + client.getClientId(), client.getClientId());
        }
    }

    @Override
    public void delete(Integer clientId) {
        try (PreparedStatement statement = getStatement(DELETE_CLIENT_QUERY)) {
            statement.setInt(1, clientId);
            statement.execute();
            logger.debug("Delete Client By client Id = " + clientId);
        } catch (SQLException e) {
            logger.error("Delete Client exception" + e);
            throw new DAOException("Delete Client exception clientId = " + clientId, clientId);
        }
    }

    private Client setClient(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setClientId(resultSet.getInt("client_id"));
        client.setLogin(resultSet.getString("client_login"));
        client.setMySQLpassword(resultSet.getString("client_password"));
        client.setMail(resultSet.getString("client_mail"));
        return client;
    }

    private PreparedStatement getStatement(String query) throws SQLException {
        Connection con = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = con.prepareStatement(query);
        return statement;
    }
}
