package co.edu.uptc.classworkdinamic.services;

import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.List;


import co.edu.uptc.classworkdinamic.exeptions.ProjectExeption;
import co.edu.uptc.classworkdinamic.exeptions.TypeMessage;
import co.edu.uptc.classworkdinamic.models.City;
import co.edu.uptc.classworkdinamic.utils.Config;
import co.edu.uptc.services.managerFileService.ManagerInFileTxtService;
import co.edu.uptc.services.managerFileService.ManagerOutFileTxtService;

public class CityService {

    private Config config;

    public CityService(){
        config = new Config();
    }
  

    public List<City> getCities() throws ProjectExeption{
        ManagerInFileTxtService managerInFileTxtService = new ManagerInFileTxtService();
        managerInFileTxtService.setFileName(config.getCityPath());
        List<String> citiesTxt = new ArrayList<String>();
        List<City> cities = new ArrayList<City>();
        try {
            citiesTxt = managerInFileTxtService.getInfoStrings();
            for (String string : citiesTxt) {
                String[] parts = string.split(",");
                City city = new City();
                city.setCodeDane(parts[0]);
                city.setName(parts[1]);
                cities.add(city);
            }
        } catch (Exception e) {
            throw new ProjectExeption(TypeMessage.NOT_FOUND_FILE);
        }

        return cities;
    }


  public void addcity(City city) throws ProjectExeption {
    ManagerOutFileTxtService managerOutFileTxtService = new ManagerOutFileTxtService();
    managerOutFileTxtService.setFileName(config.getCityPath());
    try {
      managerOutFileTxtService.saveInfoStrings(makeStringFromCity(city));
    } catch (Exception e) {
     throw new ProjectExeption(TypeMessage.NOT_FOUND_FILE);
    }
  }

  
  public String makeStringFromCity(City city) {
    return city.getCodeDane() + "," + city.getName();
    }


    public City getCityByCodeDane(String codeDane) throws ProjectExeption {
        ManagerInFileTxtService managerInFileTxtService = new ManagerInFileTxtService();
        managerInFileTxtService.setFileName(config.getCityPath());
        List<String> citiesTxt = new ArrayList<String>();
        try {
            citiesTxt = managerInFileTxtService.getInfoStrings();
            for (String string : citiesTxt) {
                String[] parts = string.split(",");
                City city = new City();
                city.setCodeDane(parts[0]);
                city.setName(parts[1]);
                if (city.getCodeDane().equals(codeDane)) {
                    return city;
                }

            }
        }
        catch (Exception e) {
            throw new ProjectExeption(TypeMessage.NOT_FOUND_FILE);
        }
        return null;

    }
}
