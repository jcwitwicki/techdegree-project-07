package com.teamtreehouse.instateam.web;

public enum Status {
    ACTIVE("Active"),
    ARCHIVED("Archived"),
    NOT_STARTED("Not Started");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
