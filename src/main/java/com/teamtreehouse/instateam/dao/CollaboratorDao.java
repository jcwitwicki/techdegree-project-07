package com.teamtreehouse.instateam.dao;

import com.teamtreehouse.instateam.model.Collaborator;

import java.util.List;

public interface CollaboratorDao {

    void save(Collaborator collaborator);
    void update(Collaborator collaborator);
    void delete(Collaborator collaborator);
    List<Collaborator> findAll();
    Collaborator findById(Long id);
}
