package by.epam.bakun.booking.web.command.client;

import by.epam.bakun.booking.web.command.*;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    VIEWHOTEL {
        {
            this.command = new ViewHotelCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}