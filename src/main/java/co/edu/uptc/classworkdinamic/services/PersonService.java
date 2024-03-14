package co.edu.uptc.classworkdinamic.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.edu.uptc.classworkdinamic.exeptions.ProjectExeption;
import co.edu.uptc.classworkdinamic.exeptions.TypeMessage;
import co.edu.uptc.classworkdinamic.models.City;
import co.edu.uptc.classworkdinamic.models.Person;
import co.edu.uptc.classworkdinamic.utils.Config;
import co.edu.uptc.classworkdinamic.utils.DateUtil;
import co.edu.uptc.classworkdinamic.utils.LocalDateSerializer;
import co.edu.uptc.classworkdinamic.utils.LocalDateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    List<Person> peopleAux = new ArrayList<Person>();
    for (Person person : people) {
      if (DateUtil.getAge(person.getBirthDate()) >= 18) {
        peopleAux.add(person);
      }
    }
    return peopleAux;
  }

  public List<Person> getPeopleGender(List<Person> people,String gender) {
    List<Person> peopleAux = new ArrayList<Person>();
    for (Person person : people) {
      if (person.getGender().equals(gender)) {
        peopleAux.add(person);
      }
    }
    return peopleAux;
  }

  public List<Person> getPeopleCityByName(List<Person> people,String nameCity) {
    List<Person> peopleAux = new ArrayList<Person>();
    for (Person person : people) {
      if (person.getCity().getName().equals(nameCity)) {
        peopleAux.add(person);
      }
    }
    return peopleAux;
  }

  public void addPerson(Person person) throws ProjectExeption{
    Config config = new Config();
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
    gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());

    Gson gson = gsonBuilder.setPrettyPrinting().create();

    List<Person> people = getPeople();
    people.add(person);
    String json = gson.toJson(people);
    try {
      PrintWriter writer = new PrintWriter(config.getPeoplePath());
      writer.write(json);
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Person> getPeople() throws ProjectExeption {
    Config config = new Config();
    ArrayList<Person> people = new ArrayList<>();
    JsonReader reader = null;
    try {
      reader = new JsonReader(new FileReader(config.getPeoplePath()));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
    gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());

    Gson gson = gsonBuilder.setPrettyPrinting().create();

    Person[] peopleAux = gson.fromJson(reader, Person[].class);

    for (Person person : peopleAux) {
      people.add(person);
    }
    return people;
  }


}
