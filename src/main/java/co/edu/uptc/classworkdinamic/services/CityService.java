package co.edu.uptc.classworkdinamic.services;

import java.io.*;
import java.util.List;


import co.edu.uptc.classworkdinamic.exeptions.ProjectExeption;
import co.edu.uptc.classworkdinamic.exeptions.TypeMessage;
import co.edu.uptc.classworkdinamic.models.City;
import co.edu.uptc.classworkdinamic.models.Person;
import co.edu.uptc.classworkdinamic.utils.Config;
import co.edu.uptc.services.dynamic.UptcList;

public class CityService {


    public List<City> getCities() throws ProjectExeption {
        List<String> citiesTxt = new UptcList<String>();
        List<City> cities = new UptcList<City>();
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
        List<String> citiesTxt = new UptcList<String>();
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
        } catch (Exception e) {
            throw new ProjectExeption(TypeMessage.NOT_FOUND_FILE);
        }
        return null;

    }

    public UptcList<String> loadFile() {
        Config config = new Config();
        UptcList<String> lines = new UptcList<>();
        try (BufferedReader buffer = new BufferedReader(new FileReader(config.getCityPath()));) {
            String line = "";
            while ((line = buffer.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void saveFile(List<String> lines) {
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

    public String deleteCity(String codeDane) throws ProjectExeption {
        Config config = new Config();
        List<City> citiesTxt = this.getCities();
        List<String> newCities = new UptcList<>();
        String output = config.getCityNotDeleted();
        for (City city : citiesTxt) {
            if (city.getCodeDane().equals(codeDane)) {
                if (!this.isCityAssignedPerson(codeDane)) {
                    citiesTxt.remove(city);
                    output = config.getCityDeleted() + this.makeStringFromCity(city);
                } else {
                    newCities.add(makeStringFromCity(city));
                }
            } else {
                newCities.add(makeStringFromCity(city));
            }
        }
        this.saveFile(newCities);
        return output;

    }

    private boolean isCityAssignedPerson(String codeDane) {
        PersonService personService = new PersonService();
        List<Person> people = new UptcList<>();
        try {
            people = personService.getPeople();
        } catch (ProjectExeption e) {
            e.printStackTrace();
        }
        for (Person person : people) {
            if (person.getCity().getCodeDane().equals(codeDane)) {
                return true;
            }
        }
        return false;
    }

}
