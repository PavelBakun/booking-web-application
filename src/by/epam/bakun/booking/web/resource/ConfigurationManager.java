package by.epam.bakun.booking.web.resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("E:\\java_Projects\\newFinalTask\\config\\config.properties")) {
            prop.load(fis);
            return prop.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
