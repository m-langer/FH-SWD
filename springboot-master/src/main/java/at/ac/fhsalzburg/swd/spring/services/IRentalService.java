package at.ac.fhsalzburg.swd.spring.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import at.ac.fhsalzburg.swd.spring.dao.Rental;

public interface IRentalService {
    public Rental addRental(UUID mediaID, UUID personID, Date date, Date endDate);
    public Rental addRental(UUID mediaID, UUID personID, int weeks);

    public Rental findRental(UUID rentalId);

    public List<Rental> getRentalsPerPerson(UUID personId);
    public List<Rental> getOldRentalsPerPerson(UUID personId);

    public List<Rental> getAll();
    public List<Rental> getAllOld();

    public void removeRental(UUID rentalID);

}
