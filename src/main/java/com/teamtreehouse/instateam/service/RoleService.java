package com.teamtreehouse.instateam.service;

import com.teamtreehouse.instateam.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    void save(Role role);
    void delete(Role role);
    List<Role> findAll();
    Role findById(Long id);
    Optional<Role> findByName(String name);
}
