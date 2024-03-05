package co.edu.uptc.classworkdinamic.services;

import java.util.Comparator;

import co.edu.uptc.classworkdinamic.dtos.PersonDto;
import co.edu.uptc.classworkdinamic.models.Person;

public class ComparatorService {
    
    public static  Comparator<Person> personNameComparator(){
        return new Comparator<Person>() {

            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
    }

    public static  Comparator<Person> personLastNameComparator(){
        return new Comparator<Person>() {

            @Override
            public int compare(Person o1, Person o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        };
    }

    public static Comparator<Person> personAgeComparator() {

        return new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getBirthDate().compareTo(o2.getBirthDate());
            }
        };
        
       
    }

}
