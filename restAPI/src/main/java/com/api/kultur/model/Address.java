package com.api.kultur.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address implements Comparable<Address> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Integer id;

    @Column(name = "street")
    private String street;

    @Column(name = "number", length = 10)
    private String number;

    @Column(name = "zip")
    private Integer zip;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @ManyToOne
    @JoinColumn(name = "coordinates")
    @Fetch(FetchMode.JOIN)
    private Coordinate coordinates;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int compareTo(Address o) {
        if (o == null) {
            return 1;
        }
        if (this.id == o.id && this.street == o.street && this.number == o.number && this.zip == o.zip && this.city == o.city && this.state == o.state && this.coordinates.compareTo(o.coordinates) == 0 && this.country == o.country) {
            return 0;
        } else {
            return 1;
        }
    }
}