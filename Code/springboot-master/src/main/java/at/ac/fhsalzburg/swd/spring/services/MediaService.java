package at.ac.fhsalzburg.swd.spring.services;

import at.ac.fhsalzburg.swd.spring.dao.Media;

public class MediaService implements IMediaService {

    @Override
    public Media getMedia(int iD) {
        return new Media();
    };

    @Override
    public void saveMedia(Media media) {
        
    };
}