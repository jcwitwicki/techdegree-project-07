package com.teamtreehouse.instateam.model;


import com.teamtreehouse.instateam.validation.ProjectUpdateConstraint;
import com.teamtreehouse.instateam.validation.UniqueProjectConstraint;
import com.teamtreehouse.instateam.web.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(groups = {UniqueProjectConstraint.class, ProjectUpdateConstraint.class})
    @Size(min = 1, max = 26, groups = {UniqueProjectConstraint.class, ProjectUpdateConstraint.class})
    @UniqueProjectConstraint(groups = UniqueProjectConstraint.class)
    private String name;

    @NotNull
    @Size(min = 3, max = 1024)
    private String description;

    private String status;


    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Role> rolesNeeded = new ArrayList<>();

    @ManyToMany
    private List<Collaborator> collaborators = new ArrayList<>();

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

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColorStatus() {
        return Status.valueOf(toEnumSyntax(getStatus())).getHexCode();
    }

    public List<Role> getRolesNeeded() { return rolesNeeded; }

    public void setRolesNeeded(List<Role> rolesNeeded) { this.rolesNeeded = rolesNeeded; }

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<Collaborator> collaborators) {
        this.collaborators = collaborators;
    }

    private String toEnumSyntax(String status) {
        status = status.toUpperCase();
        for (int i=0;i<status.length();i++) {
            if (status.charAt(i) == ' ') {
                status = status.substring(0,i) + "_" + status.substring(i+1);
            }
        }
        return status;
    }

}
