package com.example.clientapi.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "visitsRole")
    private boolean visitsRole;

    @Column(name = "timeStamp")
    @Temporal(TemporalType.DATE)
    private LocalDate timeStamp;

    public Visit() {
    }

    public Visit(String name, String lastName, boolean VisitsRole) {
        this.name = name;
        this.lastName = lastName;
        this.visitsRole = VisitsRole;
        this.timeStamp = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String description) {
        this.lastName = description;
    }

    public boolean isVisitsRole() {
        return visitsRole;
    }

    public void setVisitsRole(boolean published) {
        this.visitsRole = published;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Visit [id=" + id + ", title=" + name + ", desc=" + lastName + ", published=" + visitsRole + "]";
    }
}