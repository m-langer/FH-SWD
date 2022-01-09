package at.ac.fhsalzburg.swd.spring.forms;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import at.ac.fhsalzburg.swd.spring.enums.mediaCategory;
import at.ac.fhsalzburg.swd.spring.enums.mediaType;

@Entity
public class MediaForm {

    private String name;
    private String author;
    private mediaType type;
    private String iSBN;
    private String fSK;
    private int length;
    private mediaCategory category;

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
