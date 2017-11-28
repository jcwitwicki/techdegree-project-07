package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping({"/","/index"})
    public String listProjects(Model model) {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "project/index";
    }

    @RequestMapping("/index/{projectId}")
    public String project(@PathVariable Long projectId, Model model) {
        if (!model.containsAttribute("project")) {
            model.addAttribute("project", projectService.findById(projectId));
        }
        return "/project/project_detail";
    }



}
