package com.teamtreehouse.instateam.model;


import com.teamtreehouse.instateam.validation.RoleUpdateConstraint;
import com.teamtreehouse.instateam.validation.UniqueRoleConstraint;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Role implements Comparable<Role> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(groups = {UniqueRoleConstraint.class, RoleUpdateConstraint.class})
    @Size(min = 1, max = 25, groups = {UniqueRoleConstraint.class, RoleUpdateConstraint.class})
    @UniqueRoleConstraint(groups = {UniqueRoleConstraint.class})
    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<Collaborator> collaborators;

    private boolean selected;

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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public int compareTo(Role o) {
        return name.compareToIgnoreCase(o.getName());
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", selected=" + selected +
                '}';
    }


}
