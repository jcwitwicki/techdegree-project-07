package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Collaborator;
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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        Project selectedProject = projectService.findById(id);
        model.addAttribute("map", getMapRoleCollaborator(selectedProject));
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
    public String updateProject(Project project, BindingResult result, RedirectAttributes redirectAttributes) {
        //        if (result.hasErrors()) {
//            redirectAttributes
//                    .addFlashAttribute("org.springframework.validation.BindingResult.project", result);
//            redirectAttributes.addFlashAttribute("project", project);
//            return "redirect:/projects/new";
//        }
        projectService.save(project);
        return "redirect:/projects/{id}";
    }

    @RequestMapping("/projects/{id}/collaborators")
    public String formEditCollaborators(@PathVariable Long id, Model model) {
        Project project = projectService.findById(id);
        if (!model.containsAttribute("project")) {
            model.addAttribute("project", project);
        }
        model.addAttribute("collaborators", collaboratorService.findAll());
        model.addAttribute("map", getMapRoleCollaborator(project));
        model.addAttribute("rolesNeeded", project.getRolesNeeded());
        model.addAttribute("action", String.format("/projects/%s/collaborators", id));

        return "/project/project_collaborators";
    }

    @RequestMapping(value = "/projects/{id}/collaborators", method = RequestMethod.POST)
    public String updateCollaborators(@PathVariable Long id, Project project, BindingResult result, RedirectAttributes redirectAttributes) {
        //        if (result.hasErrors()) {
//            redirectAttributes
//                    .addFlashAttribute("org.springframework.validation.BindingResult.project", result);
//            redirectAttributes.addFlashAttribute("project", project);
//            return "redirect:/projects/new";
//        }
        Project selectedProject = projectService.findById(id);
        selectedProject.setCollaborators(project.getCollaborators());
        projectService.save(selectedProject);
        return "redirect:/projects/{id}";
    }

    private Map<Role,Collaborator> getMapRoleCollaborator(Project project) {
        Map<Role, Collaborator> mapRoleCollaborator = new LinkedHashMap<>();
        List<Role> rolesNeeded = projectService.findRolesNeeded(project);
        List<Collaborator> collaborators  = projectService.findCollaborators(project);

        for (int i=0;i<rolesNeeded.size();i++) {

            if (collaborators.size() < rolesNeeded.size()) {
                Collaborator unassignedCollaborator = new Collaborator();
                unassignedCollaborator.setName("unassigned");
                int diff = rolesNeeded.size() - collaborators.size();
                int point = collaborators.size();
                for(int j=point;j<diff;j++) {
                    collaborators.add(j,unassignedCollaborator);
                }
            }
            mapRoleCollaborator.put(rolesNeeded.get(i),collaborators.get(i));
        }
        return mapRoleCollaborator;
    }


}
