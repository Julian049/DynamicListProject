package co.edu.uptc.classworkdinamic.controllers;

import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.classworkdinamic.dtos.Person2Dto;
import co.edu.uptc.classworkdinamic.dtos.PersonDto;
import co.edu.uptc.classworkdinamic.exeptions.ProjectExeption;
import co.edu.uptc.classworkdinamic.models.Person;
import co.edu.uptc.classworkdinamic.services.PersonService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/person")
public class PersonController {
    PersonService personService = new PersonService();

    @GetMapping()
    public ResponseEntity<Object> getPeople() {
        List<Person> peopleAux;
        try {
            peopleAux = personService.getPeople();
            return ResponseEntity.status(HttpStatus.OK).body(peopleAux);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }

    }

    @GetMapping("/all2")
    public ResponseEntity<Object> getPeople2() {
        List<Person> peopleAux;
        try {
            peopleAux = personService.getPeople();
            return ResponseEntity.status(HttpStatus.OK).body(Person2Dto.fromPeople(peopleAux));
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }

    @GetMapping("/orderName")
    public ResponseEntity<Object> getPeopleOrderName() {
        List<Person> peopleAux;
        try {
            peopleAux = personService.getPeople();
            List<Person> peopleOrder = personService.orderName(peopleAux);
            return ResponseEntity.status(HttpStatus.OK).body(PersonDto.fromPeople(peopleOrder));
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }

    }

    @GetMapping("/orderLastName")
    public ResponseEntity<Object> getPeopleOrderLastName() {
        try {
            List<Person> peopleAux = personService.getPeople();
            return ResponseEntity.status(HttpStatus.OK).body(PersonDto.fromPeople(
                    personService.orderLastName(peopleAux)));
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }

    @GetMapping("/orderAge")
    public ResponseEntity<Object> getPeopleOrderAge() {
        List<Person> peopleAux;
        try {
            peopleAux = personService.getPeople();
            return ResponseEntity.status(HttpStatus.OK).body(PersonDto.fromPeople(personService.orderAge(peopleAux)));
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }

    @GetMapping("/over18")
    public ResponseEntity<Object> getPeopleOver18() {
        List<Person> peopleAux;
        try {
            peopleAux = personService.getPeople();
            return ResponseEntity.status(HttpStatus.OK).body(
                    PersonDto.fromPeople(personService.getOver18(peopleAux)));
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }

    }

    @GetMapping("/find/gender/{gender}")
    public ResponseEntity<Object> getPeopleGender(@PathVariable String gender) {
        List<Person> peopleAux;
        try {
            peopleAux = personService.getPeople();
            return ResponseEntity.status(HttpStatus.OK).body(
                    PersonDto.fromPeople(personService.getPeopleGender(peopleAux, gender)));

        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }

    }

    @GetMapping("/find/city/{nameCity}")
    public ResponseEntity<Object> getPeopleCityByName(@PathVariable String nameCity) {
        List<Person> peopleAux;
        try {
            peopleAux = personService.getPeople();
            return ResponseEntity.status(HttpStatus.OK).body(
                    PersonDto.fromPeople(personService.getPeopleCityByName(peopleAux, nameCity)));

        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }

    }


    @PostMapping()
    public ResponseEntity<Object> addPerson(@RequestBody PersonDto personDto) {
        try {
            PersonDto.validaPerson(personDto);
            personService.addPerson(PersonDto.fromPersonDto(personDto));
            return ResponseEntity.status(HttpStatus.OK).body(personDto);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }

    }

}
