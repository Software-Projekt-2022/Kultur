package com.api.kultur.model;

import javax.persistence.*;

@Entity
@Table(name = "rating")
public class Rating implements Comparable<Rating> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id", nullable = false)
    private Integer id;

    @Column(name = "stars")
    private Integer stars;

    @Column(name = "comment")
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int compareTo(Rating o) {
        if (o == null) {
            return 1;
        }
        if (this.id == o.id && this.stars == o.stars && this.comment == o.comment) {
            return 0;
        } else {
            return 1;
        }
    }
}