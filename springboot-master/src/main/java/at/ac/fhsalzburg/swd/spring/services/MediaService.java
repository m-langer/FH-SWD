package at.ac.fhsalzburg.swd.spring.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import at.ac.fhsalzburg.swd.spring.dao.Media;
import at.ac.fhsalzburg.swd.spring.dao.MediaRepository;
import at.ac.fhsalzburg.swd.spring.enums.mediaCategory;
import at.ac.fhsalzburg.swd.spring.enums.mediaType;

@Service
public class MediaService implements IMediaService {

    @Autowired
    MediaRepository repo;

    @Override
    public Media getMedia(UUID iD) {
        return repo.findById(iD);
    };

    @Override
    public void saveMedia(Media media) {
        repo.save(media);
    }

    @Override
    public void addBook(String Name, String Author, String ISBN, int length, mediaCategory Category, boolean isSpecialized) {
        // TODO Auto-generated method stub

        Media toSave = new Media(Name, Author, isSpecialized ? mediaType.specializedBook : mediaType.simpleBook, ISBN, null, length, Category);
        repo.save(toSave);
    }

    @Override
    public void addMovie(String Name, String Author,String FSK, int length, mediaCategory Category, boolean isAdult) {
        // TODO Auto-generated method stub
        Media toSave = new Media(Name, Author, isAdult ? mediaType.movieAdult : mediaType.movie, null, FSK, length, Category);
        repo.save(toSave);
    }

    @Override
    public void addCD(String Name, String Author, int length, mediaCategory Category) {
        // TODO Auto-generated method stub
        Media toSave = new Media(Name, Author, mediaType.values()[4], null, null, length, Category);
        repo.save(toSave);
    }

    @Override
    public List<Media> getMedia(String name) {
        // TODO Auto-generated method stub
        return repo.findByName(name);
    }

    @Override
    public Iterable<Media> getAll() {
        // TODO Auto-generated method stub
        return repo.findAll();
    };
}