package at.ac.fhsalzburg.swd.spring.services;

import at.ac.fhsalzburg.swd.spring.dao.Media;
import at.ac.fhsalzburg.swd.spring.dao.PersonalData;

public interface IPersonalDataService {
    public abstract PersonalData gPersonalData(int id);
}