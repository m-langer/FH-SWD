package at.ac.fhsalzburg.swd.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import at.ac.fhsalzburg.swd.spring.dao.*;
import java.util.Collection;
import java.util.*;

public class RentalService implements IRentalService {

    @Autowired
    private RentalRepository repo;

    @Override
    public Rental getRental(int id) {
        return new Rental();
    };

    @Override
    public Collection<Rental> getRentals(PersonalData data) {
        return new ArrayList<>();
    };

    @Override
    public Collection<Rental> getRentals(Media data) {
        return new ArrayList<>();

    };

    @Override
    public Rental createRental(PersonalData pData, Media mData) {
        return new Rental();
    };
}