package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.service.CollaboratorService;
import com.teamtreehouse.instateam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CollaboratorController {

    @Autowired
    private CollaboratorService collaboratorService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/collaborators")
    public String listCollaborators(Model model) {
        List<Collaborator> collaborators = collaboratorService.findAll();
        model.addAttribute("collaborators", collaborators);
        model.addAttribute("roles", roleService.findAll());
        return "collaborator/collaborators";
    }

}
