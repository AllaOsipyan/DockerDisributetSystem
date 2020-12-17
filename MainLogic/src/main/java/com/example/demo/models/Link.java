package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "status")
public class Link {
    @Id
    @GeneratedValue
    private Long id;




    @Column(name = "status")
    private int status;

    @Column(name = "url")
    private String url;

    public Link(){
    }

    public Link(Long id, int status, String url) {
        this.status = status;
        this.url = url;
    }

    public Long getId() {
        return this.id;
    }
    public int getStatus() {
        return status;
    }
    public String getUrl() {
        return this.url;
    }
    public void setStatus(int status) {
        this.status = status;
    }

}