package by.epam.bakun.booking.web.command;

import by.epam.bakun.booking.dao.ClientDAO;
import by.epam.bakun.booking.model.Client;
import by.epam.bakun.booking.util.validate.ClientValid;
import by.epam.bakun.booking.web.resource.ConfigurationManager;
import by.epam.bakun.booking.web.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter("userName");
        String pass = request.getParameter("passId");
        String mail = request.getParameter("email");
        if (login != null && pass != null && mail != null) {
            ClientValid clientValid = new ClientValid(login, pass, mail);
            if (clientValid.isClientValid()) {
                ClientDAO clientDAO = new ClientDAO();
                Client client = new Client(login, pass, mail);
                clientDAO.create(client);
                request.setAttribute("user", login);
                page = ConfigurationManager.getProperty("path.page.main");
            }
        } else {
            request.setAttribute("errorLoginPassMessage",
                    MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
