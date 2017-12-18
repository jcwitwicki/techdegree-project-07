package com.teamtreehouse.instateam.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Collaborator implements Comparable<Collaborator> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
//    @Size(min = 1, max = 30)
    private String name;

    @ManyToOne
    private Role role;

    public Collaborator() {}

    public Collaborator(Long id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int compareTo(Collaborator o) {
        return name.compareToIgnoreCase(o.getName());
    }


}
