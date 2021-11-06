package at.ac.fhsalzburg.swd.spring.services;

import at.ac.fhsalzburg.swd.spring.dao.Media;

public interface IMediaService{
    public abstract Media getMedia(int iD);

    public abstract void saveMedia(Media media);
    
}