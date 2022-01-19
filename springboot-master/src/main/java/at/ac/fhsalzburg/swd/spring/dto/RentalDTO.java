package at.ac.fhsalzburg.swd.spring.dto;

import java.nio.LongBuffer;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

public class RentalDTO {

    private UUID rentalID;
    private UUID mediaID;
    private UUID personID;


    public UUID getMediaID() {
        return mediaID;
    }

    public UUID getRentalID() {
        return rentalID;
    }

    public void setRentalID(UUID rentalID) {
        this.rentalID = rentalID;
    }

    public void setMediaID(UUID mediaID) {
        this.mediaID = mediaID;
    }

    public UUID getPersonID() {
        return personID;
    }

    public void setPersonID(UUID personID) {
        this.personID = personID;
    }

}
