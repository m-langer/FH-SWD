package at.ac.fhsalzburg.swd.spring.dto;

import at.ac.fhsalzburg.swd.spring.enums.mediaCategory;
import at.ac.fhsalzburg.swd.spring.enums.mediaType;

public class MediaDTO {

    private String name;
    private String author;
    private mediaType type;
    private String iSBNfSK;
    private int length;
    private mediaCategory category;
    private Boolean special;
    private int shelfNr;

    public String getName() {
        return name;
    }

    public int getShelfNr() {
        return shelfNr;
    }

    public void setShelfNr(int shelfNr) {
        this.shelfNr = shelfNr;
    }

    public String getiSBNfSK() {
        return iSBNfSK;
    }

    public void setiSBNfSK(String iSBNfSK) {
        this.iSBNfSK = iSBNfSK;
    }

    public Boolean getSpecial() {
        return special;
    }

    public void setSpecial(Boolean special) {
        this.special = special;
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
