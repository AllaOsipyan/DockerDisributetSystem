package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "link")
public class Link {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "url")
    private String url;

    protected Link() { }

    public Link(Integer id, String url) {
        this.url = url;
    }

    public Long getId() {
        return this.id;
    }

    public String getUrl() {
        return this.url;
    }
}