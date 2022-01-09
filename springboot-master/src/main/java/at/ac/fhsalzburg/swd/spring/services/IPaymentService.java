package at.ac.fhsalzburg.swd.spring.services;

import java.util.UUID;

import at.ac.fhsalzburg.swd.spring.dao.Payment;

public interface IPaymentService {
    public abstract Payment getPayment(UUID iD);

    public abstract void addPayment(UUID personID, int amount, String description);
}
