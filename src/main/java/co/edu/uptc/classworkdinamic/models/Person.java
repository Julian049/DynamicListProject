package co.edu.uptc.classworkdinamic.models;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    private String typeDocument;
    private String numerDocument;
    private String name;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String city;
    
}
