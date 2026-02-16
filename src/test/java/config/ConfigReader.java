package config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    static Properties prop;

    static {
        try {
            prop = new Properties();
            prop.load(new FileInputStream("src/test/resources/config/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}