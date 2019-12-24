package is.tonlistarskolifih.TonlistarskoliFIH.Entity;

import javax.persistence.*;

@Embeddable
public class Teacher {

    private String name;
    private String abbreviation;
    private String email;
    private String phoneNumber;
    @Lob
    private String description;
    private String profession;
    private String imgRef;

    public Teacher(){

    }

    public Teacher(String name, String abbreviation, String email, String phoneNumber, String description, String profession, String imgRef) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.profession = profession;
        this.imgRef = imgRef;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getImgRef() {
        return imgRef;
    }

    public void setImgRef(String imgRef) {
        this.imgRef = imgRef;
    }
}
