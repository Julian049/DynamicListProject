package co.edu.uptc.classworkdinamic.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.classworkdinamic.models.Person;
import co.edu.uptc.classworkdinamic.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person2Dto {
     private String name;
    private String lastName;
    private int age;


    public static Person2Dto fromPerson(Person person) {
        Person2Dto person2Dto = new Person2Dto();
        person2Dto.setName(person.getName());
        person2Dto.setLastName(person.getLastName());
        person2Dto.setAge(DateUtil.getAge(person.getBirthDate()));
        return person2Dto;
    }


    public static List<Person2Dto> fromPeople(List <Person> people) {
        List <Person2Dto> personDto = new ArrayList<Person2Dto>();
        for (Person person : people) {
            personDto.add(Person2Dto.fromPerson(person));
        }
        return personDto;
    }

}
