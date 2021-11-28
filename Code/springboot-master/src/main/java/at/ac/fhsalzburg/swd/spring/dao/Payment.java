package at.ac.fhsalzburg.swd.spring.dao;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID personID;
    private int amount;
    private String text;

    public Payment() {
        
    }

    public Payment(UUID PersonID, int Amount, String Text) {
        personID = PersonID;
        amount = Amount;
        text = Text;
    };
}
