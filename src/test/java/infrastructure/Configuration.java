package infrastructure;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private Properties props = null;
    private static Configuration instance = null;

    public synchronized static Configuration getInstance() {
        if (instance == null)
            instance = new Configuration();
        return instance;
    }

    private Configuration() {
        props = new Properties();
        InputStream input;
        try {
            input = new FileInputStream("src" + File.separator + "test" + File.separator + "resources" + File.separator + "config.properties");
            props.load(input);
        } catch (Exception e) {
            Logger.getInstance().error(e.getMessage());
        }
    }

    public String getProperty(String key) {
        String property = props.getProperty(key);
        if (key.endsWith("_env")){
            return System.getenv(property);
        }
        return property;
    }
}
