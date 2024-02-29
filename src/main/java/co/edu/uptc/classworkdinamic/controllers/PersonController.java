package co.edu.uptc.classworkdinamic.controllers;

import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.classworkdinamic.models.Person;
import co.edu.uptc.classworkdinamic.services.PersonService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping("/person")
public class PersonController {
    PersonService personService = new PersonService();

    @GetMapping()
    public List<Person> getPeople(){
        return personService.getPeople();
    } 

    @GetMapping("/orderName")
    public List<Person> getPeopleOrderName() {
        List<Person> peopleAux = personService.getPeople();
        return personService.orderName(peopleAux);
    }

    @GetMapping("/orderLastName")
    public List<Person> getPeopleOrderLastName() {
        List<Person> peopleAux = personService.getPeople();
        return personService.orderLastName(peopleAux);
    }
    

    @PostMapping()
    public Person addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        return person;
    }
    


}
