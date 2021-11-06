package at.ac.fhsalzburg.swd.spring.services;

import at.ac.fhsalzburg.swd.spring.dao.Media;
import at.ac.fhsalzburg.swd.spring.dao.PersonalData;

public interface IPersonalDataService {
    public abstract PersonalData gPersonalData(int id);

    public abstract PersonalData editPersonalData(PersonalData data);

    public abstract void savePersonalData(PersonalData data);
    public abstract void savePersonalData(int id);
    
}