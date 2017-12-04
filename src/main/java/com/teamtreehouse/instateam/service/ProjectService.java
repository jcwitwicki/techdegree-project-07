package com.teamtreehouse.instateam.service;

import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.Role;

import java.util.List;

public interface ProjectService {

    void save(Project project);
    void delete(Project project);
    List<Project> findAll();
    Project findById(Long id);
    List<Role> FindRolesNeeded(Project project);
}
