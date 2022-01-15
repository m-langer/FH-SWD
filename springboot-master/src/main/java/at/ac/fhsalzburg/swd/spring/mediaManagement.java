package at.ac.fhsalzburg.swd.spring;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
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
    public
    IMediaService mediaService;

    @Autowired
    IRentalService rentalService;

    @Autowired
    IPaymentService paymentService;

    List<Media> findMedia(String name) {
        return mediaService.getMedia(name);
    }

    void rentMedia(UUID mediaID, UUID personID) {
        PersonalData pd = personalDataService.gPersonalData(personID);
        Media m = mediaService.getMedia(mediaID);
        switch (pd.getCategory()) {
            case youthCust:
                if (m.getType() != mediaType.movieAdult) {
                    rentalService.addRental(mediaID, personID, 4);
                }
                break;
            case adultCust:
                rentalService.addRental(mediaID, personID, 4);
                break;
            case studentCust:
                rentalService.addRental(mediaID, personID, 6);
                break;
            default:
                break;
        }
    }

    void returnMedia(UUID mediaID, UUID personID, UUID rentalID) {

    }
}
