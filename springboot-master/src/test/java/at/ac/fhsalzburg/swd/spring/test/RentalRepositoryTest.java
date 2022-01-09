package at.ac.fhsalzburg.swd.spring.test;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import at.ac.fhsalzburg.swd.spring.dao.Media;
import at.ac.fhsalzburg.swd.spring.dao.PersonalData;
import at.ac.fhsalzburg.swd.spring.dao.PersonalDataRepository;
import at.ac.fhsalzburg.swd.spring.dao.Rental;
import at.ac.fhsalzburg.swd.spring.dao.RentalRepository;
import at.ac.fhsalzburg.swd.spring.enums.mediaCategory;
import at.ac.fhsalzburg.swd.spring.enums.mediaType;
import at.ac.fhsalzburg.swd.spring.enums.personCategory;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class RentalRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    RentalRepository rentalRepository;

    @Test
    public void whenFindByPersonId_thenReturnRentalsFromPerson() {
        // given
        Media m = new Media("Max", "Mustermann", mediaType.specializedBook, "ISBN", null, 10, mediaCategory.biology);
        Media m2 = new Media("Max", "Mustermann", mediaType.specializedBook, "ISBN", null, 10, mediaCategory.biology);
        Media m3 = new Media("Max", "Mustermann", mediaType.specializedBook, "ISBN", null, 10, mediaCategory.biology);
        PersonalData p = new PersonalData("Max", "Mustermann", "Musterstraße", new Date(1, 1, 2000), "max@muster.com",personCategory.adultCust);
        Rental r = new Rental(m.getId(), p.getId(), new Date(), new Date());
        Rental r2 = new Rental(m2.getId(), p.getId(), new Date(), new Date());
        Rental r3 = new Rental(m3.getId(), p.getId(), new Date(), new Date());
        entityManager.persist(r);
        entityManager.persist(r2);
        entityManager.persist(r3);
        entityManager.flush();
        List<Rental> given = new ArrayList<Rental>();
        given.add(r);
        given.add(r2);
        given.add(r3);

        List<Rental> found = rentalRepository.findByPersonID(p.getId());

        assertIterableEquals(given, found);
    }
}
