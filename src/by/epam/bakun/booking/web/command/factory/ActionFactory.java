package by.epam.bakun.booking.web.command.factory;

import javax.servlet.http.HttpServletRequest;

import by.epam.bakun.booking.web.command.ActionCommand;
import by.epam.bakun.booking.web.command.EmptyCommand;
import by.epam.bakun.booking.web.command.client.CommandEnum;
import by.epam.bakun.booking.web.resource.MessageManager;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
// извлечение имени команды из запроса
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
// если команда не задана в текущем запросе
            return current;
        }
// получение объекта, соответствующего команде
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());

            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action
                    + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}