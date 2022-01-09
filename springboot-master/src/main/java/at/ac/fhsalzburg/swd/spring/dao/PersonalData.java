package at.ac.fhsalzburg.swd.spring.dao;

import java.util.Date;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import at.ac.fhsalzburg.swd.spring.enums.personCategory;

@Entity
public class PersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String lastName;
    private String adress;
    private Date birthday;
    private String eMail;
    private personCategory category;
    private float fines;

    protected PersonalData() {}

    public PersonalData(String name, String lastName, String adress, Date birthday, String eMail,
            personCategory category) {
        this.name = name;
        this.lastName = lastName;
        this.adress = adress;
        this.birthday = birthday;
        this.eMail = eMail;
        this.category = category;
        this.fines = 0;
    }

    public float getFines() {
        return fines;
    }

    public void addFines(float fines) {
        this.fines += fines;
    }

    public void removeFines() {
        this.fines = 0;
    }

    public personCategory getCategory() {
        return category;
    }

    public void setCategory(personCategory category) {
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

}