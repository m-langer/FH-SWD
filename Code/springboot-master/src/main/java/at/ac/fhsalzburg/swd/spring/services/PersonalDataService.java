package at.ac.fhsalzburg.swd.spring.services;

import at.ac.fhsalzburg.swd.spring.dao.*;
import org.springframework.beans.factory.annotation.Autowired;


public class PersonalDataService implements IPersonalDataService {

    @Autowired
    PersonalDataRepository repo;
    
    @Override
    public PersonalData gPersonalData(int id) {
        return repo.findById(id);
    };
}