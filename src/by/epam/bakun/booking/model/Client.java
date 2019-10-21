package by.epam.bakun.booking.model;

import org.apache.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Objects;

public class Client {

    private static Logger logger = Logger.getLogger(Client.class.getName());

    private int clientId;
    private String login;
    private String password;
    private String mySQLpassword;
    private String mail;

    public Client() {
    }

    public Client(String login, String password, String mail) {
        this.login = login;
        this.password = password;
        this.mail = mail;
        mySQLpassword = byteToString();
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
        mySQLpassword = byteToString();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMySQLpassword() {
        return mySQLpassword;
    }

    public void setMySQLpassword(String mySQLpassword) {
        this.mySQLpassword = mySQLpassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getClientId() == client.getClientId() &&
                Objects.equals(getLogin(), client.getLogin()) &&
                Objects.equals(mySQLpassword, client.mySQLpassword) &&
                Objects.equals(getMail(), client.getMail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClientId(), getLogin(), mySQLpassword, getMail());
    }

    private byte[] generateHash() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[]{-11, 123, 0, 99, -36, 24, 127, -35, 19, -88, 66, 42, 13, 84, -71, 1};
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return hash;

        } catch (NoSuchAlgorithmException e) {
            logger.error("Exception of generation hashPassword: " + e);
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            logger.error("Exception of generation hashPassword: " + e);
            e.printStackTrace();
        }
        return null;
    }

    private String byteToString() {
        StringBuilder builder = new StringBuilder();
        for (byte b : generateHash()) {
            builder.append(b);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.setPassword("Abcd1234");
        System.out.println(client.getMySQLpassword());
    }
}
