package at.ac.fhsalzburg.swd.spring.services;

import org.springframework.beans.factory.annotation.Autowired;

import at.ac.fhsalzburg.swd.spring.dao.*;

public class PaymentService implements IPaymentService {

    @Autowired
    PaymentRepository payRepo;
    @Autowired
    PersonalDataRepository pdRepo;

    @Override
    public Payment getPayment(PersonalData data) {
        return new Payment();
    };

    @Override
    public Payment calcPayment(PersonalData data) {
        return new Payment();
    };

    @Override
    public void externalProvider(Payment payment) {
        
    };
}