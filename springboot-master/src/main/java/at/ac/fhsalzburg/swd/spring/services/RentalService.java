package at.ac.fhsalzburg.swd.spring.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhsalzburg.swd.spring.dao.Rental;
import at.ac.fhsalzburg.swd.spring.dao.RentalOldRepository;
import at.ac.fhsalzburg.swd.spring.dao.RentalRepository;

@Service
public class RentalService implements IRentalService {
    
    @Autowired
    RentalRepository repo;

    @Autowired
    RentalOldRepository repoReturned;

    @Override
    public Rental addRental(UUID mediaID, UUID personID, Date date, Date endDate) {
        Rental toSave = new Rental(mediaID, personID, date, endDate);
        repo.save(toSave);
        return toSave;
    }

    @Override
    public Rental findRental(UUID rentalId) {
        return repo.findById(rentalId);
    }

    @Override
    public List<Rental> getRentalsPerPerson(UUID personId) {
        return repo.findByPersonID(personId);
    }
    @Override
    public List<Rental> getOldRentalsPerPerson(UUID personId) {
        return repoReturned.findByPersonID(personId);
    }

    @Override
    public Rental addRental(UUID mediaID, UUID personID, int weeks) {
        Rental toSave = new Rental(mediaID, personID, weeks);
        repo.save(toSave);
        return toSave;
    }

    @Override
    public List<Rental> getAll() {
        return (List<Rental>)repo.findAll();
    }
    @Override
    public List<Rental> getAllOld() {
        return (List<Rental>)repoReturned.findAll();
    }

    @Override
    public void removeRental(UUID rentalID) {
        repoReturned.save(repo.findById(rentalID));
        repo.deleteById(rentalID);
    }
}
