package at.ac.fhsalzburg.swd.spring.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {
    
    @Transactional(timeout = 10)
    List<Payment> findByPersonID(UUID personId);
    
    @Transactional(timeout = 10)
    Payment findById(UUID id);


}
