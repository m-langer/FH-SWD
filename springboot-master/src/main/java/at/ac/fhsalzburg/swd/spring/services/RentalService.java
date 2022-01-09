package at.ac.fhsalzburg.swd.spring.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhsalzburg.swd.spring.dao.Rental;
import at.ac.fhsalzburg.swd.spring.dao.RentalRepository;

@Service
public class RentalService implements IRentalService {
    
    @Autowired
    RentalRepository repo;

    @Override
    public Rental addRental(UUID mediaID, UUID personID, Date date, Date endDate) {
        Rental toSave = new Rental(mediaID, personID, date, endDate);
        repo.save(toSave);
        // TODO Auto-generated method stub
        return toSave;
    }

    @Override
    public Rental findRental(UUID rentalId) {
        return repo.findById(rentalId);
        // TODO Auto-generated method stub
    }

    @Override
    public List<Rental> getRentalsPerPerson(UUID personId) {
        // TODO Auto-generated method stub
        return repo.findByPersonID(personId);
    }

    @Override
    public Rental addRental(UUID mediaID, UUID personID, int weeks) {
        Rental toSave = new Rental(mediaID, personID, weeks);
        repo.save(toSave);
        // TODO Auto-generated method stub
        return toSave;
    }
    
}
