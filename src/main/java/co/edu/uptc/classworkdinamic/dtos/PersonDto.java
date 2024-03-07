package co.edu.uptc.classworkdinamic.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.core.model.processor.ProcessorException;
import co.edu.uptc.classworkdinamic.exeptions.ProjectExeption;
import co.edu.uptc.classworkdinamic.exeptions.TypeMessage;
import co.edu.uptc.classworkdinamic.models.City;
import co.edu.uptc.classworkdinamic.models.Person;
import co.edu.uptc.classworkdinamic.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {
    private String typeDocument;
    private String numerDocument;
     private String name;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private int age;
    private City city;


    public static PersonDto fromPerson(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setTypeDocument(person.getTypeDocument());
        personDto.setNumerDocument(person.getNumerDocument());
        personDto.setName(person.getName());
        personDto.setLastName(person.getLastName());
        personDto.setGender(person.getGender());
        personDto.setBirthDate(person.getBirthDate());
        personDto.setCity(person.getCity());
        personDto.setAge(DateUtil.getAge(person.getBirthDate()));
        return personDto;
    }


    public static List<PersonDto> fromPeople(List <Person> people) {
        List <PersonDto> personDto = new ArrayList<PersonDto>();
        for (Person person : people) {
            personDto.add(PersonDto.fromPerson(person));
        }
        return personDto;
    }

    public static Person fromPersonDto(PersonDto personDto) {
        Person person = new Person();
        person.setTypeDocument(personDto.getTypeDocument());
        person.setNumerDocument(personDto.getNumerDocument());
        person.setName(personDto.getName());
        person.setLastName(personDto.getLastName());
        person.setGender(personDto.getGender());
        person.setCity(personDto.getCity());
        person.setBirthDate(personDto.getBirthDate());
        return person;
    }

    public static void validaPerson(PersonDto personDto) throws ProjectExeption { 
        if (personDto.getName() == null || 
        personDto.getTypeDocument() == null ||
        personDto.getNumerDocument() == null ||
        personDto.getCity() == null ||
        personDto.getLastName() == null || 
        personDto.getGender() == null || 
        personDto.getBirthDate() == null) {
            throw new ProjectExeption(TypeMessage.INFORMATION_INCOMPLETE);
        }
        
    }


}
