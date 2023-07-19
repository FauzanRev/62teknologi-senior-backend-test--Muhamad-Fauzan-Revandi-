package com.teknologi.senior.backend.test.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name = "business")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "business_id")
    private String business_id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postal_code")
    private String postal_code;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "stars")
    private Double stars;

    @Column(name = "review_count")
    private Integer review_count;

    @Column(name = "is_open")
    private Boolean is_open;

    @Column(name = "categories")
    private String categories;

    public Business(String business_id, String name, String address, String city, String state, String postal_code, Double latitude, Double longitude, Double stars, Integer review_count, Boolean is_open, String categories) {
        this.business_id = business_id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postal_code = postal_code;
        this.latitude = latitude;
        this.longitude = longitude;
        this.stars = stars;
        this.review_count = review_count;
        this.is_open = is_open;
        this.categories = categories;
    }
}