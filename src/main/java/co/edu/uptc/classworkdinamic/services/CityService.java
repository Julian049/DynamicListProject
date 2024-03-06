package co.edu.uptc.classworkdinamic.services;

import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.classworkdinamic.dtos.CityDto;
import co.edu.uptc.classworkdinamic.exeptions.ProjectExeption;
import co.edu.uptc.classworkdinamic.exeptions.TypeMessage;
import co.edu.uptc.classworkdinamic.models.City;

public class CityService {
    List <City> cities = new ArrayList<City>();

        public CityService(){
            createCities();
        }

    public void createCities(){
        City city = new City();
        city.setCodeDane("11001");
        city.setName("BOGOTA");
        cities.add(city);

        city = new City();
        city.setCodeDane("15001");
        city.setName("TUNJA");
        cities.add(city);

        city = new City();
        city.setCodeDane("13001");
        city.setName("CARTAGENA");
        cities.add(city);
    }


    public List<City> getCities(){
        return cities;
    }

    public void add(City city) throws ProjectExeption {
        if (cities.size() < 5) {
            cities.add(city);     
        } else {
            throw new ProjectExeption(TypeMessage.NOT_SAVED);
        }
       
    }
}
