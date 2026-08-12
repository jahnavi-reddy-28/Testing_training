package pac;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    Properties prop;

    public PropertyReader() throws IOException {

        prop = new Properties();

        FileInputStream fis =
                new FileInputStream(
                        System.getProperty("user.dir")
                        + "\\configuration\\config.properties");

        prop.load(fis);
    }

    public String getProperty(String key) {

        return prop.getProperty(key);
    }
}