package by.epam.bakun.booking.util.validate;

public class ClientValid {
    private final static String LOGIN_REG = "^([A-Za-z])[\\w+]{5,}";
    private final static String PASS_REG = "(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])[A-Za-z0-9]{6,}";
    private final static String MAIL_REG = "^.+@.+\\..+$";

    private String uName;
    private String passId;
    private String uEmail;

    public ClientValid(String uName, String passId, String uEmail) {
        this.passId = passId;
        this.uName = uName;
        this.uEmail = uEmail;
    }

    public boolean isLoginValid() {
        boolean bool = uName.matches(LOGIN_REG);
         return bool;
    }

    public boolean isPassValid() {
        boolean bool = passId.matches(PASS_REG);
        return bool;
    }

    public boolean isMailValid() {
        boolean bool = uEmail.matches(MAIL_REG);
        return bool;
    }

    public boolean isClientValid() {
        return (isLoginValid() &&
                isPassValid() &&
                isMailValid());
    }

    public static void main(String[] args) {

    }
}
