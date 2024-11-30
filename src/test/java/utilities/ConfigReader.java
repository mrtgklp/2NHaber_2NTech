package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties properties;
    static {
        String dosyaYolu = "src/Configuration.properties";

        try {
            FileInputStream fileInputStream = new FileInputStream(dosyaYolu);
            properties= new Properties();
            properties.load(fileInputStream);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        String value = properties.getProperty(key);
        return value;
    }
}
