package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.CollaboratorService;
import com.teamtreehouse.instateam.service.ProjectService;
import com.teamtreehouse.instateam.service.RoleService;
import com.teamtreehouse.instateam.web.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping("/projects/{id}")
    public String projectDetails(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("project")) {
            model.addAttribute("project", projectService.findById(id));
        }
        List<Role> rolesNeeded = projectService.findRolesNeeded(projectService.findById(id));
        model.addAttribute("rolesNeeded", rolesNeeded);
        return "/project/details";
    }

    @RequestMapping("/projects/new")
    public String formNewProject(Model model) {
        if (!model.containsAttribute("project")) {
            model.addAttribute("project", new Project());
        }
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("statuses", Status.values());
        model.addAttribute("action", "/projects");
        model.addAttribute("submit", "Save");
        return "project/form";
    }

    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    public String addProject( Project project, BindingResult result, RedirectAttributes redirectAttributes) {

//        if (result.hasErrors()) {
//            redirectAttributes
//                    .addFlashAttribute("org.springframework.validation.BindingResult.project", result);
//            redirectAttributes.addFlashAttribute("project", project);
//            return "redirect:/projects/new";
//        }
        projectService.save(project);
        return "redirect:/projects";
    }

    @RequestMapping("/projects/{id}/edit")
    public String formEditProject(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("project")) {
            model.addAttribute("project", projectService.findById(id));
        }
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("statuses", Status.values());
        model.addAttribute("action", String.format("/projects/%s/edit", id));
        model.addAttribute("submit", "Update");
        return "/project/form";
    }

    @RequestMapping(value = "/projects/{id}/edit", method = RequestMethod.POST)
    public String updateProject( Project project, BindingResult result, RedirectAttributes redirectAttributes) {
        //        if (result.hasErrors()) {
//            redirectAttributes
//                    .addFlashAttribute("org.springframework.validation.BindingResult.project", result);
//            redirectAttributes.addFlashAttribute("project", project);
//            return "redirect:/projects/new";
//        }
        projectService.save(project);
        return "redirect:/projects";
    }

}
