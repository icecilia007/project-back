package com.brzas.project.alternative.models;

public class AlternativeDTO {
    private String description;
    private char option;

    public AlternativeDTO(String description, char option) {
        this.description = description;
        this.option = option;
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
}
