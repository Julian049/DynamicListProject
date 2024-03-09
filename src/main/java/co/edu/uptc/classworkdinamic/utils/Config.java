package co.edu.uptc.classworkdinamic.utils;

import java.util.ResourceBundle;

public class Config {

    private static ResourceBundle resourceBundle;

    public void init() {
        resourceBundle = ResourceBundle.getBundle("main/resources/config");
    }

    public static String getCityPath(){
        return resourceBundle.getString("cityPath");
    }

    public static String getPeoplePath(){
        return resourceBundle.getString("peoplePath");
    }
}
