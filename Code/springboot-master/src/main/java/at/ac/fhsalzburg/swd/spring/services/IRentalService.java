package at.ac.fhsalzburg.swd.spring.services;

import java.util.Collection;

import at.ac.fhsalzburg.swd.spring.dao.Media;
import at.ac.fhsalzburg.swd.spring.dao.PersonalData;
import at.ac.fhsalzburg.swd.spring.dao.Rental;

public interface IRentalService{
    public abstract Rental getRental(int id);

    public abstract Collection<Rental> getRentals(PersonalData data);
    public abstract Collection<Rental> getRentals(Media data);

    public abstract Rental createRental(PersonalData pData, Media mData);
}