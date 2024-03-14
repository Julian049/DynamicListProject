package co.edu.uptc.classworkdinamic.utils;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;

public class Config {

    private Properties properties = new Properties();;

    public Config() {
        loadProperties();
    }

    private void loadProperties() {
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
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

    public String getNotFoundMessage(){
        return properties.getProperty("notFoundMessage");
    }

    public String getNotSavedMessage(){
        return properties.getProperty("notSavedMessage");
    }

    public String getInformationIncomplete(){
        return properties.getProperty("informationIncomplete");
    }

    public String getFileNotFoundMessage(){
        return properties.getProperty("fileNotFoundMessage");
    }

    public String getSavedMessage(){
        return properties.getProperty("savedMessage");
    }
}
