package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.service.CollaboratorService;
import com.teamtreehouse.instateam.service.ProjectService;
import com.teamtreehouse.instateam.service.RoleService;
import com.teamtreehouse.instateam.web.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CollaboratorService collaboratorService;

    @Autowired
    private RoleService roleService;

    @RequestMapping({"/","/projects"})
    public String listProjects(Model model) {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "project/projects";
    }

    @RequestMapping("/projects/{projectId}")
    public String projectDetails(@PathVariable Long projectId, Model model) {
        if (!model.containsAttribute("project")) {
            model.addAttribute("project", projectService.findById(projectId));
        }
        return "/project/details";
    }

    @RequestMapping("/project/{projectId}/edit")
    public String formEditProject(@PathVariable Long projectId, Model model) {
        if (!model.containsAttribute("project")) {
            model.addAttribute("project", projectService.findById(projectId));
        }
        return "/project/edit";
    }


    @RequestMapping("projects/new")
    public String formNewProject(Model model) {
        if (!model.containsAttribute("project")) {
            model.addAttribute("project", new Project());
        }
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("statuses", Status.values());
        return "project/new";
    }

    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    public String addProject(Project project) {
        projectService.save(project);
        return "redirect:/projects";
    }



}
