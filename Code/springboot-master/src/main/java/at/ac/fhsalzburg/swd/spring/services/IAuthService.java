package at.ac.fhsalzburg.swd.spring.services;

import at.ac.fhsalzburg.swd.spring.dao.AuthClaim;
import at.ac.fhsalzburg.swd.spring.dao.PersonalData;

public interface IAuthService{
    public abstract AuthClaim getClaim(int iD);

    public abstract void registerUser(PersonalData newCustomer);

    public abstract void loginUser(String username, String password);
}