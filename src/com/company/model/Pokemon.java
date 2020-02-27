package com.company.model;

import java.time.LocalDate;

public class Pokemon {

    private String name;
    private int level;
    private LocalDate captureDate;

    public Pokemon() { }

    public Pokemon(String name, int level, LocalDate captureDate) {
        setName(name);
        setLevel(level);
        setCaptureDate(captureDate);
    }

    public String getName() {
        return this.name;
    }

    // Make sure the name isn't empty
    // Make sure the name isn't null
    public void setName(String name) {
        validateNonNull(name, "name");
        if(name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        this.name = name;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        if(level <= 0 || level > 100) {
           throw new IllegalArgumentException("Level must be integer between 1 and 100");
        }
        this.level = level;
    }

    public LocalDate getCaptureDate() {
        return this.captureDate;
    }

    public void setCaptureDate(LocalDate captureDate) {
        validateNonNull(captureDate, "captureDate");
        this.captureDate = captureDate;
    }

    private void validateNonNull(Object param, String name) {
        if(param == null) {
            throw new IllegalArgumentException(name + " cannot be null");
        }
    }

    @Override
    public String toString() {
        return "Level " + this.level + " " + this.name + ", captured on "
                + this.captureDate.toString();
    }

    // Serialize
    public String serialize() {
        // Pidgey|3|January 3 2020
        return String.format("%s|%d|%s", this.name, this.level, this.captureDate.toString());
    }

    // Deserialize
    // This pokemon takes in a string and sets its own properties based on that
    public void deserialize(String lineFromFile) {
        String[] pieces = lineFromFile.split("\\|");
        this.setName(pieces[0].trim());
        this.setLevel(Integer.parseInt(pieces[1].trim()));
        this.setCaptureDate(LocalDate.parse(pieces[2].trim()));
    }


}






