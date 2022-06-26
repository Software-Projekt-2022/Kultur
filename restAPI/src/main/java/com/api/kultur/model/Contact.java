package com.api.kultur.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "contact")
public class Contact implements Comparable<Contact> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "website")
    private String website;

    @OneToOne
    @JoinColumn(name = "address")
    @Fetch(FetchMode.JOIN)
    private Address address;

    @Column(name = "image")
    private String image;

    @Column(name = "hours")
    private String hours;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @OneToMany
    @JoinColumn(name = "ratings")
    @Fetch(FetchMode.JOIN)
    private Set<Rating> rating = new java.util.LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Rating> getRating() {
        return rating;
    }

    public void setRating(Set<Rating> rating) {
        this.rating = rating;
    }

    /**
     * adds a rating to Set
     * @param rating the rating to add
     */
    public void addRating(Rating rating) {
        this.rating.add(rating);
    }

    @Override
    public int compareTo(Contact o) {
        if (o == null) {
            return 1;
        }
        if (this.id.equals(o.id) && this.name.equals(o.name) && this.phone.equals(o.phone) && this.website.equals(o.website) && this.address.compareTo(o.address) == 0 && this.image.equals(o.image) && this.hours.equals(o.hours) && this.category.equals(o.category) && this.description.equals(o.description) && this.rating.equals(o.rating)) {
            return 0;
        } else {
            return 1;
        }
    }
}