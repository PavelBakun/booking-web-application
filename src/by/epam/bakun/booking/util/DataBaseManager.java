package by.epam.bakun.booking.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataBaseManager {
    private DataBaseManager() {
    }

    public static String getProperty(String key) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("E:\\java_Projects\\newFinalTask\\config\\db.properties")) {
            prop.load(fis);
            return prop.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
