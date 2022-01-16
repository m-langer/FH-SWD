package at.ac.fhsalzburg.swd.spring.forms;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import at.ac.fhsalzburg.swd.spring.enums.personCategory;


public class PersonalDataForm {
    private String name;
    private String lastName;
    private String adress;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String eMail;
    private personCategory category;
    private Boolean isStudent;

    public String getName() {
        return name;
    }
    public Boolean getIsStudent() {
        return isStudent;
    }
    public void setIsStudent(Boolean isStudent) {
        this.isStudent = isStudent;
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
    public personCategory getCategory() {
        return category;
    }
    public void setCategory(personCategory category) {
        this.category = category;
    }

}
