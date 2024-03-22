package co.edu.uptc.classworkdinamic.utils;

import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;

public class Config {

    private Properties properties = new Properties();

    public Config() {
        loadProperties();
    }

    private void loadProperties() {
        try {
            properties.load(new FileInputStream("C:/Users/Julian/Documents/Universidad/Cuarto_Semestre/Programacion_III/DynamicListProject/src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCityPath() {
        return properties.getProperty("cityPath");
    }

    public String getPeoplePath() {
        return properties.getProperty("peoplePath");
    }

    public String getCityNotDeleted() {
        return properties.getProperty("cityNotDeleted");
    }

    public String getCityDeleted() {
        return properties.getProperty("cityDeleted");
    }


}
