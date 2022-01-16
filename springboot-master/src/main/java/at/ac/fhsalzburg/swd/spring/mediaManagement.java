package at.ac.fhsalzburg.swd.spring;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhsalzburg.swd.spring.dao.Media;
import at.ac.fhsalzburg.swd.spring.dao.PersonalData;
import at.ac.fhsalzburg.swd.spring.dao.Rental;
import at.ac.fhsalzburg.swd.spring.enums.mediaType;
import at.ac.fhsalzburg.swd.spring.services.IMediaService;
import at.ac.fhsalzburg.swd.spring.services.IPaymentService;
import at.ac.fhsalzburg.swd.spring.services.IPersonalDataService;
import at.ac.fhsalzburg.swd.spring.services.IRentalService;

@Service
public class mediaManagement {

    @Autowired
    IPersonalDataService personalDataService;

    @Autowired
    public IMediaService mediaService;

    @Autowired
    IRentalService rentalService;

    @Autowired
    IPaymentService paymentService;

    List<Media> findMedia(String name) {
        return mediaService.getMedia(name);
    }

    void rentMedia(UUID mediaID, UUID personID) throws Exception {
        PersonalData pd = personalDataService.gPersonalData(personID);
        Media m = mediaService.getMedia(mediaID);
        switch (pd.getCategory()) {
            case youthCust:
                if (m.getType() != mediaType.movieAdult) {
                    rentalService.addRental(mediaID, personID, 4);
                } else {
                    throw new Exception();
                }
                break;
            case adultCust:
                rentalService.addRental(mediaID, personID, 4);
                break;
            case studentCust:
                if (m.getType() == mediaType.specializedBook) {
                    rentalService.addRental(mediaID, personID, 6);
                } else {
                    rentalService.addRental(mediaID, personID, 4);
                }
                break;
            default:
                break;
        }
        m.setIsRented(true);
        mediaService.saveMedia(m);
    }

    void returnMedia(UUID rentalID, UUID mediaID, UUID personID) {
        Rental r = rentalService.findRental(rentalID);
        PersonalData pd = personalDataService.gPersonalData(r.getPersonID());
        Media m = mediaService.getMedia(r.getMediaID());
        if (pd.getId() != personID) {
            throw new IllegalArgumentException();
        }
        if (m.getId() != mediaID) {
            throw new IllegalArgumentException();
        }
        if (r.getLatestReturn().after(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
            rentalService.removeRental(rentalID);
            m.setIsRented(false);
            mediaService.saveMedia(m);
        } else {
            long daysBetween = ChronoUnit.DAYS.between(
                    r.getLatestReturn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    LocalDateTime.now());
            long amountToPay = daysBetween * 3;
            paymentService.addPayment(personID, amountToPay,
                    "Days over time: " + daysBetween + " Fine is 3€ per Day. Fine Amount: " + amountToPay + ".");
            pd.addFines(amountToPay);
            personalDataService.savePersonalData(pd);
            rentalService.removeRental(rentalID);
            m.setIsRented(false);
            mediaService.saveMedia(m);
        }
    }
    void returnMedia(UUID rentalID) {
        Rental r = rentalService.findRental(rentalID);
        PersonalData pd = personalDataService.gPersonalData(r.getPersonID());
        Media m = mediaService.getMedia(r.getMediaID());

        if (r.getLatestReturn().after(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
            rentalService.removeRental(rentalID);
            m.setIsRented(false);
            mediaService.saveMedia(m);
        } else {
            long daysBetween = ChronoUnit.DAYS.between(
                    r.getLatestReturn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    LocalDateTime.now());
            long amountToPay = daysBetween * 3;
            paymentService.addPayment(pd.getId(), amountToPay,
                    "Days over time: " + daysBetween + " Fine is 3€ per Day. Fine Amount: " + amountToPay + ".");
            pd.addFines(amountToPay);
            personalDataService.savePersonalData(pd);
            rentalService.removeRental(rentalID);
            m.setIsRented(false);
            mediaService.saveMedia(m);
        }
    }
}
