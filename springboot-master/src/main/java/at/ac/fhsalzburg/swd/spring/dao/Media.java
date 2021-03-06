package at.ac.fhsalzburg.swd.spring.dao;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import at.ac.fhsalzburg.swd.spring.enums.mediaCategory;
import at.ac.fhsalzburg.swd.spring.enums.mediaType;

@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String author;
    private mediaType type;
    private String iSBN;
    private String fSK;
    private int length;
    private mediaCategory category;
    private int shelfNr;
    private Boolean isRented;

    protected Media() {}

    public Boolean getIsRented() {
        return isRented;
    }

    public void setIsRented(Boolean isRented) {
        this.isRented = isRented;
    }

    public int getShelfNr() {
        return shelfNr;
    }

    public void setShelfNr(int shelfNr) {
        this.shelfNr = shelfNr;
    }

    public Media(String name, String author, mediaType type, String iSBN, String fSK, int length,
            mediaCategory category, int shelfNr) {
        this.name = name;
        this.author = author;
        this.type = type;
        this.iSBN = iSBN;
        this.fSK = fSK;
        this.length = length;
        this.category = category;
        this.shelfNr = shelfNr;
        this.isRented = false;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public mediaType getType() {
        return type;
    }

    public void setType(mediaType type) {
        this.type = type;
    }

    public String getiSBN() {
        return iSBN;
    }

    public void setiSBN(String iSBN) {
        this.iSBN = iSBN;
    }

    public String getfSK() {
        return fSK;
    }

    public void setfSK(String fSK) {
        this.fSK = fSK;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public mediaCategory getCategory() {
        return category;
    }

    public void setCategory(mediaCategory category) {
        this.category = category;
    }

}
