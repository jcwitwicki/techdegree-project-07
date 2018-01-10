package com.teamtreehouse.instateam.web;

public enum Status {
    ACTIVE("Active", "Green", "#72c38d"),
    ARCHIVED("Archived", "Grey", "#bbbab9"),
    NOT_STARTED("Not Started", "Blue", "#5976b3"),
    TESTING("Testing", "Orange", "#b36859");

    private final String name;
    private final String color;
    private final String hexCode;

    Status(String name, String color, String hexCode) {
        this.name = name;
        this.color = color;
        this.hexCode = hexCode;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getHexCode() {
        return hexCode;
    }
}
