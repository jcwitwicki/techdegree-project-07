package com.teamtreehouse.instateam.service;

import com.teamtreehouse.instateam.model.Role;

import java.util.List;

public interface RoleService {

    void save(Role role);
    void update(Role role);
    void delete(Role role);
    List<Role> findAll();
    Role findById(Long id);
}
