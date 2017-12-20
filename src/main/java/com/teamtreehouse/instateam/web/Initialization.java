package com.teamtreehouse.instateam.web;

import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.CollaboratorService;
import com.teamtreehouse.instateam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Initialization implements ApplicationRunner {

    @Autowired
    private RoleService roleService;

    @Autowired
    private CollaboratorService collaboratorService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Collaborator> collaborators = collaboratorService.findAll();

        if (collaborators.isEmpty()) {
            Role unassignedRole = new Role();
            unassignedRole.setName("-Unassigned-");
            roleService.save(unassignedRole);

            Collaborator unassignedCollaborator = new Collaborator();
            unassignedCollaborator.setName("-Unassigned-");
            unassignedCollaborator.setRole(unassignedRole);

            collaboratorService.save(unassignedCollaborator);
        }
    }
}

