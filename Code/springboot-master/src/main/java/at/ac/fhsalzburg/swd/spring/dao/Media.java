package at.ac.fhsalzburg.swd.spring.dao;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String author;
    private mediaType type;
    private String ISBN;
    private String FSK;
    private int length;
    private category category;

    public enum mediaType {
        simpleBook, specializedBook, movie, movieAdult, cd
    }

    public enum category {
        maths, physics, biology, thriller, fantasy, romance, action
    }

    public Media() {
    };
}
