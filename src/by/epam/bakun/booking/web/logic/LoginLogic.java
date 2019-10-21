package by.epam.bakun.booking.web.logic;

import by.epam.bakun.booking.dao.ClientDAO;
import by.epam.bakun.booking.exception.DAOException;
import by.epam.bakun.booking.model.Client;

public class LoginLogic {
    public static boolean checkLogin(String enterLogin, String enterPass) {
        try {
            ClientDAO clientDAO = new ClientDAO();
            Client client = clientDAO.getByLogin(enterLogin);
            String newPassword, passOfDB;
            passOfDB = client.getMySQLpassword();
            client.setPassword(enterPass);
            newPassword = client.getMySQLpassword();
            return passOfDB.equals(newPassword);
        } catch (DAOException | NullPointerException e) {
            return false;
        }
    }
}
