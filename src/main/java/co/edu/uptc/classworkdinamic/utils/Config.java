package co.edu.uptc.classworkdinamic.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private Properties properties;

    public Config() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream inputStream = getClass().getResourceAsStream("main/resources/config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCityPath() {
        return properties.getProperty("cityPath");
    }

    public String getPeoplePath() {
        return properties.getProperty("personPath");
    }
}
