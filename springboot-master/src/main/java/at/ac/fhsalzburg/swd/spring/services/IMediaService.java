package at.ac.fhsalzburg.swd.spring.services;

import java.util.List;
import java.util.UUID;

import at.ac.fhsalzburg.swd.spring.dao.*;
import at.ac.fhsalzburg.swd.spring.enums.mediaCategory;

public interface IMediaService {
    public abstract Media getMedia(UUID iD);
    public abstract List<Media> getMedia(String name);

    public abstract void saveMedia(Media media);

    public abstract void addBook(String Name, String Author, String ISBN, int length, mediaCategory Category, boolean isSpecialized);

    public abstract void addMovie(String Name, String Author,String FSK, int length, mediaCategory Category, boolean isAdult);

    public abstract void addCD(String Name, String Author, int length, mediaCategory Category);
}