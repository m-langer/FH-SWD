package at.ac.fhsalzburg.swd.spring.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhsalzburg.swd.spring.dao.Payment;
import at.ac.fhsalzburg.swd.spring.dao.PaymentRepository;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    PaymentRepository repo;

    @Override
    public Payment getPayment(UUID iD) {
        return repo.findById(iD);
    }

    @Override
    public void addPayment(UUID personID, int amount, String description) {
        repo.save(new Payment(personID, amount, description));
    }
    
}
