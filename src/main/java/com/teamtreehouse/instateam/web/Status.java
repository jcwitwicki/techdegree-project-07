package com.teamtreehouse.instateam.web;

public enum Status {
    ACTIVE("Active"),
    ARCHIVED("Archived"),
    NOT_STARTED("Not Started"),
    TESTING("Testing");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
