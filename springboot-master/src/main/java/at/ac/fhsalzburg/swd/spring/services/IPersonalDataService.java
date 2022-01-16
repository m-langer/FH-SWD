package at.ac.fhsalzburg.swd.spring.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import at.ac.fhsalzburg.swd.spring.dao.PersonalData;

public interface IPersonalDataService {
    public abstract PersonalData gPersonalData(UUID id);

    public abstract Iterable<PersonalData> getAll();

    public abstract PersonalData addData(String Name,
            String Lastname,
            String Adress,
            Date Birthday,
            String email);

    public abstract PersonalData addData(String Name,
            String Lastname,
            String Adress,
            Date Birthday,
            String email, Boolean isStudent);

    public abstract List<PersonalData> getByLastName(String Lastname);

    public abstract void changeToAdult(UUID personID);

    public abstract void savePersonalData(PersonalData personalData);
}