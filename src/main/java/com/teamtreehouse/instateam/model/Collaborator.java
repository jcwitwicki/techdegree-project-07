package com.teamtreehouse.instateam.model;

import com.teamtreehouse.instateam.validation.UniqueCollaboratorConstraint;
import com.teamtreehouse.instateam.validation.CollaboratorUpdateConstraint;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Collaborator implements Comparable<Collaborator> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(groups = {UniqueCollaboratorConstraint.class, CollaboratorUpdateConstraint.class})
    @Size(min = 2, max = 20, groups = {UniqueCollaboratorConstraint.class, CollaboratorUpdateConstraint.class})
    @UniqueCollaboratorConstraint(groups = {UniqueCollaboratorConstraint.class})
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
