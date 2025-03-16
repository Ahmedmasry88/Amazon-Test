package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        try {
            String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\config.properties"; 
            FileInputStream file = new FileInputStream(filePath);
            properties.load(file);
            file.close(); // Closing the file after loading
        } catch (IOException e) {
            System.out.println("Failed to load config.properties. Check the file path.");
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
