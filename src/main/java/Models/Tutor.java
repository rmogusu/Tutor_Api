package Models;

import java.util.Objects;

public class Tutor {
    private String name;
    private String experience;
    private String rating;
    private String phone;
    private String subjects;
    private String bio;
    private  String imageUrl;
    private int id;
    public Tutor(String name, String experience, String  imageUrl ,String rating, String phone, String subjects, String bio) {
        this.name = name;
        this.experience = experience;
        this.rating = rating;
        this.phone = phone;
        this.bio = bio;
        this.imageUrl = imageUrl;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tutor )) return false;
       Tutor  tutor = (Tutor  ) o;
        return id == tutor .id &&
                Objects.equals(name, tutor.name) &&
                Objects.equals(phone, tutor.phone) &&
                Objects.equals(bio, tutor.bio)&&
                Objects.equals(rating, tutor.rating) &&
                Objects.equals(imageUrl, tutor.imageUrl)&&
                Objects.equals(subjects, tutor.subjects)&&
                Objects.equals(experience  , tutor.experience);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone , bio , rating, imageUrl, subjects,experience, id);
    }
}
