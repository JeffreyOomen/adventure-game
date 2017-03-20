package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.domain.util.Media;

public interface MediaService {

    /**
     * findById
     * find a Media object
     * @param id Long
     * @return Media
     */
    Media findById(long id);

    /**
     * save
     * creates a new Media object
     * @param media obj
     * @return Media
     */
    Media save(Media media);

}
