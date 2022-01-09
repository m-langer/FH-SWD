package at.ac.fhsalzburg.swd.spring.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {

	@Transactional(timeout = 10)
	Rental findById(UUID id);

	@Transactional(timeout = 10)
	List<Rental> findByPersonID(UUID id);

}
