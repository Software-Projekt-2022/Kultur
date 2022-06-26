package com.api.kultur.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event")
public class Event implements Comparable<Event> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "place")
    @Fetch(FetchMode.JOIN)
    private Coordinate place;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "max_people")
    private Integer maxPeople;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Coordinate getPlace() {
        return place;
    }

    public void setPlace(Coordinate place) {
        this.place = place;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    @Override
    public int compareTo(Event o) {
        if (o == null) {
            return 1;
        }
        if (this.id.equals(o.id) && this.place.compareTo(o.place) == 0 && this.date.equals(o.date) && this.name.equals(o.name) && this.description.equals(o.description) && this.category.equals(o.category) && this.maxPeople.equals(o.maxPeople)) {
            return 0;
        } else {
            return 1;
        }
    }
}