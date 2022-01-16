package at.ac.fhsalzburg.swd.spring.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.ac.fhsalzburg.swd.spring.enums.mediaCategory;

@Repository
public interface MediaRepository extends CrudRepository<Media, Long> {
	
	@Transactional(timeout = 10)
	Media findById(UUID id);
	@Transactional(timeout = 10)
	List<Media> findByName(String name);
	@Transactional(timeout = 10)
	List<Media> findByNameContains(String name);
	@Transactional(timeout = 10)
	List<Media> findByAuthorContains(String Author);
	@Transactional(timeout = 10)
	List<Media> findByiSBNContains(String isbn);
	@Transactional(timeout = 10)
	List<Media> findByfSKContains(String fSK);
	@Transactional(timeout = 10)
	List<Media> findByCategory(mediaCategory category);
	@Transactional(timeout = 10)
	List<Media> findByNameLike(String name);
}
