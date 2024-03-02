package co.edu.uptc.classworkdinamic.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.edu.uptc.classworkdinamic.models.Person;

public class PersonService {
   private List<Person> people = new ArrayList<Person>();


    public PersonService() {
        loadPeople();
    }

     public List<Person> loadPeople() {
         
         Person person = new Person();
         person.setName("Gerardo");
         person.setLastName("zzVazquez");
         people.add(person);

         person = new Person();
         person.setName("AAAAMiguel");
         person.setLastName("bbbVazquez");
         people.add(person);

         person = new Person();
         person.setName("Miguel");
         person.setLastName("aaaVazquez");
         people.add(person);

         return people;
     }


     public List<Person>  orderName(List<Person> peopleAux) {
         Collections.sort(peopleAux,ComparatorService.personNameComparator());
         return peopleAux;
     }


    public List<Person> getPeople() {
  List <Person> peopleAux = new ArrayList<Person>();
    for (Person person : this.people) {
        peopleAux.add(person);
    }
       return peopleAux;
    }

    public List<Person> orderLastName(List<Person> peopleAux) {
        Collections.sort(peopleAux,ComparatorService.personLastNameComparator());
        return peopleAux;
    }

  public void addPerson(Person person){
    people.add(person);
  }

}
