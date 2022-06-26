package com.api.kultur.model;

import javax.persistence.*;

@Entity
@Table(name = "coordinate")
public class Coordinate implements Comparable<Coordinate> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coordinate_id", nullable = false)
    private Integer id;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public int compareTo(Coordinate o) {
        if (o == null) {
            return 1;
        }
        if (this.id == o.id && this.longitude == o.longitude && this.latitude == o.latitude) {
            return 0;
        } else {
            return 1;
        }
    }
}