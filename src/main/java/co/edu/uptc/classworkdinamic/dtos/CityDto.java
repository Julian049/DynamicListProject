package co.edu.uptc.classworkdinamic.dtos;

import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.classworkdinamic.models.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDto {
    private String name;
    private String codeDane;


    public static CityDto fromCity(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setCodeDane(city.getCodeDane());
        cityDto.setName(city.getName());
        return cityDto;
    }

    public static City fromCityDto(CityDto cityDto) {
        City city = new City();
        city.setCodeDane(cityDto.getCodeDane());
        city.setName(cityDto.getName());
        return city;
    }

    public static List<CityDto> fromCities(List<City> cities) {
        List<CityDto> cityDtos = new ArrayList<>();
        for (City city : cities) {
            cityDtos.add(fromCity(city));
        }
        return cityDtos;
    }


}
