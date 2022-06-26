package com.api.kultur.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "club")
public class Club implements Comparable<Club> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "logo_path")
    private String logoPath;

    @Lob
    @Column(name = "cost")
    private String cost;

    @Lob
    @Column(name = "category")
    private String category;

    @Lob
    @Column(name = "meetings")
    private String meetings;

    @OneToOne
    @JoinColumn(name = "contact")
    @Fetch(FetchMode.JOIN)
    private Contact contact;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMeetings() {
        return meetings;
    }

    public void setMeetings(String meetings) {
        this.meetings = meetings;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public int compareTo(Club o) {
        if (o == null) {
            return 1;
        }
        if (this.name == o.name && this.id == o.id && this.description == o.description && this.logoPath == o.logoPath && this.cost == o.cost && this.category == o.category && this.meetings == o.meetings && this.contact.compareTo(o.contact) == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}