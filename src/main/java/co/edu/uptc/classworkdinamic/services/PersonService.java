package co.edu.uptc.classworkdinamic.services;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import co.edu.uptc.classworkdinamic.exeptions.ProjectExeption;
import co.edu.uptc.classworkdinamic.exeptions.TypeMessage;
import co.edu.uptc.classworkdinamic.models.Person;
import co.edu.uptc.classworkdinamic.utils.DateUtil;
import co.edu.uptc.services.SimpleLIst;
import co.edu.uptc.services.managerFileService.ManagerInFileTxtService;
import co.edu.uptc.services.managerFileService.ManagerOutFileTxtService;

public class PersonService {
  

  public List<Person> orderName(List<Person> peopleAux) {
    Collections.sort(peopleAux, ComparatorService.personNameComparator());
    return peopleAux;
  }


  public List<Person> orderLastName(List<Person> peopleAux) {
    Collections.sort(peopleAux, ComparatorService.personLastNameComparator());
    return peopleAux;
  }

  
  public List<Person> orderAge(List<Person> peopleAux) {
    Collections.sort(peopleAux, ComparatorService.personAgeComparator());
    return peopleAux;
  }

  public List<Person> getOver18(List<Person> people) {
    List<Person> peopleAux = new SimpleLIst<Person>();
    for (Person person : people) {
      if (DateUtil.getAge(person.getBirthDate()) >= 18) {
        peopleAux.add(person);
      }
    }
    return peopleAux;
  }

  public List<Person> getPeopleGender(List<Person> people,String gender) {
    List<Person> peopleAux = new SimpleLIst<Person>();
    for (Person person : people) {
      if (person.getGender().equals(gender)) {
        peopleAux.add(person);
      }
    }
    return peopleAux;
  }

  public List<Person> getPeopleCityByName(List<Person> people,String nameCity) {
    List<Person> peopleAux = new SimpleLIst<Person>();
    for (Person person : people) {
      if (person.getCity().getName().equals(nameCity)) {
        peopleAux.add(person);
      }
    }
    return peopleAux;
  }

  public void addPerson(Person person) throws ProjectExeption {
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


  public List<Person> getPeople() throws ProjectExeption{
    CityService cityService = new CityService();
    List<Person> people = new SimpleLIst<Person>();
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
        person.setCity(cityService.getCityByCodeDane(parts[5]));
        person.setBirthDate(LocalDate.parse(parts[6]));
        people.add(person);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new ProjectExeption(TypeMessage.NOT_FOUND_FILE);
    }
    return people;
  }


}
