package at.ac.fhsalzburg.swd.spring.services;

import at.ac.fhsalzburg.swd.spring.dao.*;
import at.ac.fhsalzburg.swd.spring.enums.personCategory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalDataService implements IPersonalDataService {

    @Autowired
    PersonalDataRepository repo;

    @Override
    public PersonalData addData(String Name, String Lastname, String Adress, Date Birthday, String email) {
        // TODO Auto-generated method stub
        PersonalData toSave;
        Date todayMinus18Years = Date
                .from(LocalDate.now().minusYears(18).atStartOfDay(ZoneId.systemDefault()).toInstant());
        if (Birthday.after(todayMinus18Years)) {
            toSave = new PersonalData(Name, Lastname, Adress, Birthday, email, personCategory.youthCust);
        } else {
            toSave = new PersonalData(Name, Lastname, Adress, Birthday, email, personCategory.adultCust);
        }
        repo.save(toSave);
        return toSave;
    }

    @Override
    public PersonalData addData(String Name, String Lastname, String Adress, Date Birthday, String email,
            Boolean isStudent) {
        PersonalData toSave;
        if (isStudent) {
            toSave = new PersonalData(Name, Lastname, Adress, Birthday, email, personCategory.adultCust);
            repo.save(toSave);
            return toSave;
        } else {
            return addData(Name, Lastname, Adress, Birthday, email);
        }
    }

    @Override
    public PersonalData gPersonalData(UUID id) {
        return repo.findById(id);
    }

    @Override
    public Iterable<PersonalData> getAll() {
        return repo.findAll();
    }

    @Override
    public List<PersonalData> getByLastName(String Lastname) {
        return repo.findByLastName(Lastname);
    }

    @Override
    public void changeToAdult(UUID personID) {
        Date todayMinus18Years = Date
                .from(LocalDate.now().minusYears(18).atStartOfDay(ZoneId.systemDefault()).toInstant());
        PersonalData pd = repo.findById(personID);
        if (pd.getBirthday().before(todayMinus18Years)) {
            pd.setCategory(personCategory.adultCust);
        }
        repo.save(pd);
    }
}