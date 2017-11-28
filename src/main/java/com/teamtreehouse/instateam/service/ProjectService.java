package com.teamtreehouse.instateam.service;

import com.teamtreehouse.instateam.model.Project;

import java.util.List;

public interface ProjectService {

    void save(Project project);
    void delete(Project project);
    List<Project> findAll();
    Project findById(Long id);

}
