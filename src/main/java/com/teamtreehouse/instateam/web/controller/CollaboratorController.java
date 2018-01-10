package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.service.CollaboratorService;
import com.teamtreehouse.instateam.service.RoleService;
import com.teamtreehouse.instateam.validation.UniqueCollaboratorConstraint;
import com.teamtreehouse.instateam.validation.CollaboratorUpdateConstraint;
import com.teamtreehouse.instateam.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        if(!model.containsAttribute("collaborator")){
            model.addAttribute("collaborator", new Collaborator());
        }
        model.addAttribute("roles", roleService.findAll());
        return "collaborator/collaborators";
    }

    @RequestMapping("/collaborators/{id}")
    public String formEditCollaborator(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("collaborator")) {
            model.addAttribute("collaborator", collaboratorService.findById(id));
        }
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("action", String.format("/collaborators/%s",id));
        return "collaborator/edit";
    }

    @RequestMapping(value = "/collaborators", method = RequestMethod.POST)
    public String addCollaborator (@Validated(UniqueCollaboratorConstraint.class) Collaborator collaborator, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.collaborator", result);
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Please try again", FlashMessage.Status.FAILURE));
            redirectAttributes.addFlashAttribute("collaborator", collaborator);
            return "redirect:/collaborators";
        }
        collaboratorService.save(collaborator);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Collaborator successfully added", FlashMessage.Status.SUCCESS));
        return "redirect:/collaborators";
    }

    @RequestMapping(value = "/collaborators/{id}", method = RequestMethod.POST)
    public String updateCollaborator (@Validated(CollaboratorUpdateConstraint.class) Collaborator collaborator, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.collaborator", result);
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Please try again", FlashMessage.Status.FAILURE));
            redirectAttributes.addFlashAttribute("collaborator", collaborator);
            return "redirect:/collaborators/{id}";
        }
        collaboratorService.save(collaborator);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Collaborator successfully updated", FlashMessage.Status.SUCCESS));
        return "redirect:/collaborators";
    }

}