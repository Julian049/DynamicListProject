package co.edu.uptc.classworkdinamic.controllers;

import java.util.List;

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
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp())
            .body(e.getMenssage());
        }
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

    @GetMapping("/delete/{codeDane}")
    public ResponseEntity<Object> deleteCity(@PathVariable String codeDane) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.deleteCity(codeDane));
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }

    }

}
