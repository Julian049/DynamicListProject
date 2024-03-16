package co.edu.uptc.classworkdinamic.controllers;

import java.util.List;

import co.edu.uptc.classworkdinamic.dtos.PersonDto;
import co.edu.uptc.classworkdinamic.models.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.uptc.classworkdinamic.dtos.CityDto;
import co.edu.uptc.classworkdinamic.exeptions.ProjectExeption;
import co.edu.uptc.classworkdinamic.models.City;
import co.edu.uptc.classworkdinamic.services.CityService;

@RestController
@RequestMapping("/city")
public class CityController {
    CityService service = new CityService();

    @GetMapping()
    public ResponseEntity<Object> getCities() {
        List<City> cities;
        try {
            cities = service.getCities();
            return ResponseEntity.status(HttpStatus.OK).body(cities);
                //CityDto.fromCities(cities));
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp())
            .body(e.getMenssage());
        }

        //return ResponseEntity.ok(CityDto.fromCities(cities));
    }

    @PostMapping()
    public ResponseEntity<Object> postCity(@RequestBody CityDto cityDto1) {

        try {
            service.addcity(CityDto.fromCityDto(cityDto1));
            return ResponseEntity.status(HttpStatus.OK).body(cityDto1);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp())
                    .body(e.getMenssage());
        }
    }

    @GetMapping("/delete/{city}")
    public ResponseEntity<Object> getDeleteCity(@PathVariable String city) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.deleteCity(city));
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }

    }

}
