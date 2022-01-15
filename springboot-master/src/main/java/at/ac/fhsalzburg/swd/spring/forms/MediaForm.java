package at.ac.fhsalzburg.swd.spring.forms;

import at.ac.fhsalzburg.swd.spring.enums.mediaCategory;
import at.ac.fhsalzburg.swd.spring.enums.mediaType;

public class MediaForm {

    private String name;
    private String author;
    private mediaType type;
    private String iSBNfSK;
    private int length;
    private mediaCategory category;
    private Boolean special;

    public String getName() {
        return name;
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
