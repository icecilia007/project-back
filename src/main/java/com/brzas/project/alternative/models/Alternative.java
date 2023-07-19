package com.brzas.project.alternative.models;

import jakarta.persistence.*;

@Entity
@Table(name = "alternative")
public class Alternative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alternative_id")
    private long id;
    @Column(name = "description")
    private String description;
    @Column(name = "option_choice")
    private char option;

    public Alternative() {
    }

    public Alternative(String description, char option) {
        this.description = description;
        this.option = option;
    }

    public Alternative(long id, String description, char option) {
        this.id = id;
        this.description = description;
        this.option = option;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public char getOption() {
        return option;
    }

    public void setOption(char option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "Alternative{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", option=" + option +
                '}';
    }
}
