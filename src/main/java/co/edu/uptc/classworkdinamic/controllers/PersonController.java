package co.edu.uptc.classworkdinamic.controllers;

import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.classworkdinamic.dtos.Person2Dto;
import co.edu.uptc.classworkdinamic.dtos.PersonDto;
import co.edu.uptc.classworkdinamic.models.Person;
import co.edu.uptc.classworkdinamic.services.PersonService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping("/person")
public class PersonController {
    PersonService personService = new PersonService();

    @GetMapping()
    public List<PersonDto> getPeople(){
        List<Person> peopleAux = personService.getPeople();
        return PersonDto.fromPeople(peopleAux);
    } 

    @GetMapping("/all2")
    public List<Person2Dto> getPeople2(){
        List<Person> peopleAux = personService.getPeople();
        return Person2Dto.fromPeople(peopleAux);
    } 

    @GetMapping("/orderName")
    public List<PersonDto> getPeopleOrderName() {
        List<Person> peopleAux = personService.getPeople();
        return PersonDto.fromPeople(personService.orderName(peopleAux));
    }

    @GetMapping("/orderLastName")
    public List<PersonDto> getPeopleOrderLastName() {
        List<Person> peopleAux = personService.getPeople();
        return PersonDto.fromPeople(personService.orderLastName(peopleAux));
    }

    @GetMapping("/orderAge")
    public List<PersonDto> getPeopleOrderAge() {
        List<Person> peopleAux = personService.getPeople();
        return PersonDto.fromPeople(personService.orderAge(peopleAux));
    }

    @GetMapping("/over18")
    public List<PersonDto> getPeopleOver18() {
        return PersonDto.fromPeople(personService.getOver18());
    }

    @GetMapping("/find/{gender}")
    public List<PersonDto> getPeopleGender(@PathVariable String gender) {
       return PersonDto.fromPeople(personService.getPeopleGender(gender));    
    }
    




    
    

    @PostMapping()
    public PersonDto addPerson(@RequestBody PersonDto personDto) {
        if (PersonDto.validaPerson(personDto)) {
           personService.addPerson(PersonDto.fromPersonDto(personDto));
        }
        return personDto;
    }
    


}
