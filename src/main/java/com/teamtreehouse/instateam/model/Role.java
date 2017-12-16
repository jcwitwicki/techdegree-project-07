package com.teamtreehouse.instateam.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Role implements Comparable<Role> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
//    @Size(min = 1, max = 15)
    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<Collaborator> collaborators;

    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<Collaborator> collaborators) {
        this.collaborators = collaborators;
    }

    @Override
    public int compareTo(Role o) {
        return name.compareToIgnoreCase(o.getName());
    }

}
