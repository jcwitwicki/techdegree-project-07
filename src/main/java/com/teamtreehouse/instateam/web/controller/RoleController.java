package com.teamtreehouse.instateam.web.controller;

import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.CollaboratorService;
import com.teamtreehouse.instateam.service.RoleService;
import com.teamtreehouse.instateam.validation.RoleUpdateConstraint;
import com.teamtreehouse.instateam.validation.UniqueRoleConstraint;
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
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private CollaboratorService collaboratorService;

    @RequestMapping("/roles")
    public String listRoles(Model model) {
        List<Role> roles = roleService.findAll();
        if(!model.containsAttribute("role")){
            model.addAttribute("role", new Role());
        }
        model.addAttribute("roles",roles);
        return "role/roles";
    }

    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public String addRole(@Validated(UniqueRoleConstraint.class) Role role, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.role", result);
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Please try again", FlashMessage.Status.FAILURE));
            redirectAttributes.addFlashAttribute("role", role);
            return "redirect:/roles";
        }
        roleService.save(role);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Role successfully added", FlashMessage.Status.SUCCESS));
        return "redirect:/roles";
    }

    @RequestMapping("/roles/{id}")
    public String formEditRole (@PathVariable Long id, Model model) {
        if (!model.containsAttribute("role")) {
            model.addAttribute("role", roleService.findById(id));
        }
        model.addAttribute("action", String.format("/roles/%s",id));
        return "role/edit";
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.POST)
    public String updateRole (@Validated(RoleUpdateConstraint.class) Role role, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.role", result);
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Please try again", FlashMessage.Status.FAILURE));
            redirectAttributes.addFlashAttribute("role", role);
            return "redirect:/roles/{id}";
        }
        roleService.save(role);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Role successfully updated", FlashMessage.Status.SUCCESS));
        return "redirect:/roles";
    }
}


