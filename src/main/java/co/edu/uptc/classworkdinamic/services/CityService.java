package co.edu.uptc.classworkdinamic.services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import co.edu.uptc.classworkdinamic.exeptions.ProjectExeption;
import co.edu.uptc.classworkdinamic.exeptions.TypeMessage;
import co.edu.uptc.classworkdinamic.models.City;
import co.edu.uptc.classworkdinamic.utils.Config;

public class CityService {
  

    public List<City> getCities() throws ProjectExeption{
        List<String> citiesTxt = new ArrayList<String>();
        List<City> cities = new ArrayList<City>();
        try {
            citiesTxt = this.loadFile();
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
    try {
      List<String> pp = this.loadFile();
        pp.add(makeStringFromCity(city));
        this.saveFile(pp);
    } catch (Exception e) {
     throw new ProjectExeption(TypeMessage.NOT_FOUND_FILE);
    }
  }

  
  public String makeStringFromCity(City city) {
    return city.getCodeDane() + "," + city.getName();
    }


    public City getCityByCodeDane(String codeDane) throws ProjectExeption {
        List<String> citiesTxt = new ArrayList<String>();
        try {
            citiesTxt = this.loadFile();
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

    public ArrayList<String> loadFile() throws IOException {
        Config config = new Config();
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader buffer = new BufferedReader(new FileReader(config.getCityPath()));) {
            String line = "";
            while ((line = buffer.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void saveFile(List<String> lines) throws IOException {
        Config config = new Config();
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(config.getCityPath()))) {
            for (String line : lines) {
                buffer.write(line);
                buffer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
