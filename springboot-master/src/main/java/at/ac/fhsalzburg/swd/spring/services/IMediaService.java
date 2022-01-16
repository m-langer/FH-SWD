package at.ac.fhsalzburg.swd.spring.services;

import java.util.List;
import java.util.UUID;

import at.ac.fhsalzburg.swd.spring.dao.*;
import at.ac.fhsalzburg.swd.spring.enums.mediaCategory;

public interface IMediaService {
    public abstract Media getMedia(UUID iD);

    public abstract List<Media> getMedia(String name);
    public abstract List<Media> getMediaByAuthor(String author);
    public abstract List<Media> getMediaByISBN(String isbn);
    public abstract List<Media> getMediaByCategory(mediaCategory category);

    public abstract Iterable<Media> getAll();
    public abstract Iterable<Media> getAllNotRented();

    public abstract void saveMedia(Media media);

    public abstract void addBook(String Name, String Author, String ISBN, int length, mediaCategory Category,
            boolean isSpecialized, int shelfNr);

    public abstract void addMovie(String Name, String Author, String FSK, int length, mediaCategory Category,
            boolean isAdult, int shelfNr);

    public abstract void addCD(String Name, String Author, int length, mediaCategory Category, int shelfNr);
}