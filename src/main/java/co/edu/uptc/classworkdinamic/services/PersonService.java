package co.edu.uptc.classworkdinamic.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.edu.uptc.classworkdinamic.dtos.PersonDto;
import co.edu.uptc.classworkdinamic.models.Person;
import co.edu.uptc.classworkdinamic.utils.DateUtil;

public class PersonService {
  private List<Person> people = new ArrayList<Person>();

  public PersonService() {
    loadPeople();
  }

  public List<Person> loadPeople() {

    Person person = new Person();
    person.setName("Gerardo");
    person.setLastName("zzVazquez");
    person.setGender("Male");
    person.setBirthDate(LocalDate.of(1980, 05, 05));
    people.add(person);

    person = new Person();
    person.setName("AAAAMiguel");
    person.setLastName("bbbVazquez");
    person.setGender("Male");
    person.setBirthDate(LocalDate.of(2009, 01, 05));
    people.add(person);

    person = new Person();
    person.setName("Maria");
    person.setLastName("aaaVazquez");
    person.setGender("Female");
    person.setBirthDate(LocalDate.of(2001, 06, 05));
    people.add(person);

    return people;
  }

  public List<Person> orderName(List<Person> peopleAux) {
    Collections.sort(peopleAux, ComparatorService.personNameComparator());
    return peopleAux;
  }

  public List<Person> getPeople() {
    List<Person> peopleAux = new ArrayList<Person>();
    for (Person person : this.people) {
      peopleAux.add(person);
    }
    return peopleAux;
  }

  public List<Person> orderLastName(List<Person> peopleAux) {
    Collections.sort(peopleAux, ComparatorService.personLastNameComparator());
    return peopleAux;
  }

  public void addPerson(Person person) {
    people.add(person);
  }

  public List<Person> orderAge(List<Person> peopleAux) {
    Collections.sort(peopleAux, ComparatorService.personAgeComparator());
    return peopleAux;
  }

  public List<Person> getOver18() {
    List<Person> peopleAux = new ArrayList<Person>();
    for (Person person : people) {
      if (DateUtil.getAge(person.getBirthDate()) >= 18) {
        peopleAux.add(person);
      }
    }
    return peopleAux;
  }

public List<Person> getPeopleGender(String gender) {
  List<Person> peopleAux = new ArrayList<Person>();
  for (Person person : people) {
    if (person.getGender().equals(gender)) {
      peopleAux.add(person);
    }
  }
  return peopleAux;
}



}
