package co.edu.uptc.classworkdinamic.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.edu.uptc.classworkdinamic.dtos.PersonDto;
import co.edu.uptc.classworkdinamic.exeptions.ProjectExeption;
import co.edu.uptc.classworkdinamic.exeptions.TypeMessage;
import co.edu.uptc.classworkdinamic.models.Person;
import co.edu.uptc.classworkdinamic.utils.DateUtil;
import co.edu.uptc.services.managerFileService.ManagerInFileTxtService;
import co.edu.uptc.services.managerFileService.ManagerOutFileTxtService;

public class PersonService {
  private List<Person> people = new ArrayList<Person>();

  public PersonService() {
    //loadPeople();
  }

  public List<Person> loadPeople11() {

    Person person = new Person();
    person.setTypeDocument("CC");
    person.setNumerDocument("123456789");
    person.setName("Gerardo");
    person.setLastName("zzVazquez");
    person.setGender("Male");
    person.setCity("250810");
    person.setBirthDate(LocalDate.of(1980, 05, 05));
    people.add(person);

    person = new Person();
    person.setTypeDocument("CC");
    person.setNumerDocument("987654321");
    person.setName("AAAAMiguel");
    person.setLastName("bbbVazquez");
    person.setGender("Male");
    person.setCity("250810");
    person.setBirthDate(LocalDate.of(2009, 01, 05));
    people.add(person);

    person = new Person();
    person.setTypeDocument("CC");
    person.setNumerDocument("123456789");
    person.setName("Maria");
    person.setLastName("aaaVazquez");
    person.setGender("Female");
    person.setCity("250810");
    person.setBirthDate(LocalDate.of(2001, 06, 05));
    people.add(person);

    return people;
  }

  public List<Person> orderName(List<Person> peopleAux) {
    Collections.sort(peopleAux, ComparatorService.personNameComparator());
    return peopleAux;
  }

  public List<Person> getPeople() {
    try {
      load();
    } catch (ProjectExeption e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
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

  public void addPerson(Person person) throws ProjectExeption {
    people.add(person);
    save(person);
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

  public void save(Person person) throws ProjectExeption {
    ManagerOutFileTxtService managerOutFileTxtService = new ManagerOutFileTxtService();
    managerOutFileTxtService.setFileName("people.txt");
    try {
      managerOutFileTxtService.saveInfoStrings(makeStringFromPerson(person));
    } catch (Exception e) {
     throw new ProjectExeption(TypeMessage.NOT_FOUND_FILE);
    }
  }

  public String makeStringFromPerson(Person person) {
    return person.getTypeDocument() + "," +
        person.getNumerDocument() + "," +
        person.getName() + "," +
        person.getLastName() + "," +
        person.getGender() + ","
        + person.getCity() + ","
        + person.getBirthDate();
  }


  public void load() throws ProjectExeption{
    ManagerInFileTxtService managerInFileTxtService = new ManagerInFileTxtService();
    managerInFileTxtService.setFileName("people.txt");
    try {
      List<String> pp = managerInFileTxtService.getInfoStrings();
      for (String string : pp) {
        String[] parts = string.split(","); 
        Person person = new Person();
        person.setTypeDocument(parts[0]);
        person.setNumerDocument(parts[1]);
        person.setName(parts[2]);
        person.setLastName(parts[3]);
        person.setGender(parts[4]);
        person.setCity(parts[5]);
        person.setBirthDate(LocalDate.parse(parts[6]));
        people.add(person);
      }


    } catch (Exception e) {
      throw new ProjectExeption(TypeMessage.NOT_FOUND_FILE);
    }


  }
}
