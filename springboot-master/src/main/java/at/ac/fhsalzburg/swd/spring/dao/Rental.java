package at.ac.fhsalzburg.swd.spring.dao;

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

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID mediaID;
    private UUID personID;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private Date latestReturn;

    protected Rental() {}

    public Rental(UUID mediaID, UUID personID, Date date, Date latestReturn) {
        this.mediaID = mediaID;
        this.personID = personID;
        this.latestReturn = latestReturn;
    }
    public Rental(UUID mediaID, UUID personID, int weeks) {
        this.mediaID = mediaID;
        this.personID = personID;
        this.latestReturn = Date
                .from(LocalDate.now().plusWeeks(weeks).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Date getLatestReturn() {
        return latestReturn;
    }

    public void setLatestReturn(Date latestReturn) {
        this.latestReturn = latestReturn;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getMediaID() {
        return mediaID;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    

}
