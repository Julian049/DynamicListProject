package co.edu.uptc.classworkdinamic.dtos;


import co.edu.uptc.classworkdinamic.models.City;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDto {
    private String name;
    private String codeDane;

    public static City fromCityDto(CityDto cityDto) {
        City city = new City();
        city.setCodeDane(cityDto.getCodeDane());
        city.setName(cityDto.getName());
        return city;
    }
    

}
