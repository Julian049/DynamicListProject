package co.edu.uptc.classworkdinamic.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


import co.edu.uptc.classworkdinamic.exeptions.ProjectExeption;
import co.edu.uptc.classworkdinamic.exeptions.TypeMessage;
import co.edu.uptc.classworkdinamic.utils.Config;
import co.edu.uptc.classworkdinamic.models.City;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.web.bind.annotation.PathVariable;

public class CityService {

    public List<City> getCities() throws ProjectExeption{
        Config config = new Config();

            ArrayList<City> cities = new ArrayList<>();
            JsonReader reader = null;
            try {
                reader = new JsonReader(new FileReader(config.getCityPath()));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            Gson gson = new Gson();

            City[] citiesAux = gson.fromJson(reader, City[].class);
            for (City city : citiesAux) {
                cities.add(city);
            }
            return cities;

    }


  public void addcity(City city) throws ProjectExeption {
      Config config = new Config();
      Gson gson = new Gson();
      List<City> cities = getCities();
      cities.add(city);
      String json = gson.toJson(cities);
      try {
          PrintWriter writer = new PrintWriter(config.getCityPath());
          writer.write(json);
          writer.close();
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
  }

    public City getCityByCodeDane(String codeDane) throws ProjectExeption {
            List<City> cities = this.getCities();
            for (City city : cities) {
                if (city.getCodeDane().equals(codeDane)) {
                    return city;
                }
            }
            return null;
    }
}
