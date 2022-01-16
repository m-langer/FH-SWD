package at.ac.fhsalzburg.swd.spring.services;

import java.util.List;
import java.util.UUID;

import at.ac.fhsalzburg.swd.spring.dao.Payment;

public interface IPaymentService {
    public abstract Payment getPayment(UUID iD);

    public abstract List<Payment> getAll();

    public abstract List<Payment> getPaymentsPerPerson(UUID personID);

    public abstract void addPayment(UUID personID, long amount, String description);
}
