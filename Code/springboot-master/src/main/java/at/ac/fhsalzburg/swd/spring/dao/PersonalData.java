package at.ac.fhsalzburg.swd.spring.dao;

import java.util.Date;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID personID;
    private String Name;
    private String Lastname;
    private String Adress;
    private Date Birthday;
    private String email;
}