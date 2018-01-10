package com.teamtreehouse.instateam.service;

import com.teamtreehouse.instateam.model.Collaborator;

import java.util.List;
import java.util.Optional;

public interface CollaboratorService {

    void save(Collaborator collaborator);
    void delete(Collaborator collaborator);
    List<Collaborator> findAll();
    Collaborator findById(Long id);
    Optional<Collaborator> findByName(String name);
}
