package at.ac.fhsalzburg.swd.spring.services;

import at.ac.fhsalzburg.swd.spring.dao.Media;
import at.ac.fhsalzburg.swd.spring.dao.Payment;
import at.ac.fhsalzburg.swd.spring.dao.PersonalData;

public interface IPaymentService{
    public abstract Payment getPayment(PersonalData data);

    public abstract Payment calcPayment(PersonalData data);

    public abstract void externalProvider(Payment payment);
}