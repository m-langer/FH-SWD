package at.ac.fhsalzburg.swd.spring.services;

import at.ac.fhsalzburg.swd.spring.dao.AuthClaim;

public interface IAuthService{
    public abstract AuthClaim getClaim(int ID);
    
}