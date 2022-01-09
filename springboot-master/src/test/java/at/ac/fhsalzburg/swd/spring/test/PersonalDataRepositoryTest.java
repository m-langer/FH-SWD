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

import at.ac.fhsalzburg.swd.spring.dao.PersonalData;
import at.ac.fhsalzburg.swd.spring.dao.PersonalDataRepository;
import at.ac.fhsalzburg.swd.spring.enums.personCategory;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class PersonalDataRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    PersonalDataRepository personalDataRepository;

    @Test
    public void whenFindByName_thenReturnPersonalData() {
        //given
        PersonalData pD = new PersonalData("Max", "Mustermann", "Musterstraße 1", new Date(1,1,1999),
                "max.mustermann@mail.com",personCategory.adultCust);
        entityManager.persist(pD);
        entityManager.flush();
        List<PersonalData> given = new ArrayList<PersonalData>();
        given.add(pD);

        List<PersonalData> found = personalDataRepository.findByLastName(pD.getLastName());

        assertIterableEquals(given, found);

    }
}
