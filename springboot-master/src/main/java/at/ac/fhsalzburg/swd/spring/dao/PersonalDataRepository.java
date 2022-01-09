package at.ac.fhsalzburg.swd.spring.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonalDataRepository extends CrudRepository<PersonalData, Long> {

	@Transactional(timeout = 10)
    List<PersonalData> findByLastName(String lastName);

	@Transactional(timeout = 10)
	PersonalData findById(UUID id);

}
