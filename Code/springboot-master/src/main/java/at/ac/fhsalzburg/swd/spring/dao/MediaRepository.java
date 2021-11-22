package at.ac.fhsalzburg.swd.spring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MediaRepository extends CrudRepository<Media, Long> {

	@Transactional(timeout = 10)
    List<Media> findByLastName(String lastName);

	@Transactional(timeout = 10)
	Media findById(long id);
	
	
}
