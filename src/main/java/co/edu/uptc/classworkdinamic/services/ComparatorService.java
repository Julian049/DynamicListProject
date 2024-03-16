package co.edu.uptc.classworkdinamic.services;

import java.util.Comparator;

import co.edu.uptc.classworkdinamic.models.Person;
import co.edu.uptc.services.dynamic.UptcList;

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

    public static void bubbleDates(UptcList<Person> list){
        for(int i=0;i<list.size();i++){
            Person temp;
            for(int j=i+1;j<list.size();j++){
                if((list.get(i).getBirthDate().compareTo(list.get(j).getBirthDate()) > 0)){
                    temp=list.get(j);
                    list.set(j,list.get(i));
                    list.set(i,temp);
                }
            }
        }
    }

    public static void bubbleNames(UptcList<Person> list){
        for(int i=0;i<list.size();i++){
            Person temp;
            for(int j=i+1;j<list.size();j++){
                if((list.get(i).getName().compareTo(list.get(j).getName()) > 0)){
                    temp=list.get(j);
                    list.set(j,list.get(i));
                    list.set(i,temp);
                }
            }
        }
    }

    public static void bubbleLastNames(UptcList<Person> list){
        for(int i=0;i<list.size();i++){
            Person temp;
            for(int j=i+1;j<list.size();j++){
                if((list.get(i).getLastName().compareTo(list.get(j).getLastName()) > 0)){
                    temp=list.get(j);
                    list.set(j,list.get(i));
                    list.set(i,temp);
                }
            }
        }
    }
}
