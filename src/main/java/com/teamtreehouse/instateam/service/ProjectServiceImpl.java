package com.teamtreehouse.instateam.service;

import com.teamtreehouse.instateam.dao.ProjectDao;
import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public void save(Project project) {
        projectDao.save(project);
    }

    @Override
    public void delete(Project project) {
        projectDao.delete(project);
    }

    @Override
    public List<Project> findAll() {
        return projectDao.findAll();
    }

    @Override
    public Project findById(Long id) {
        return projectDao.findById(id);
    }

    @Override
    public List<Role> findRolesNeeded(Project project) {
        List<Role> rolesNeeded = project.getRolesNeeded();
        return rolesNeeded;
    }

    @Override
    public List<Collaborator> findCollaborators(Project project) {
        List<Collaborator> collaborators = project.getCollaborators();
        return collaborators;
    }


}
