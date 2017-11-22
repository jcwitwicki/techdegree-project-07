package com.teamtreehouse.instateam.dao;


import com.teamtreehouse.instateam.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Project project) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(project);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Project project) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(project);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Project project) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(project);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Project> findAll() {
        Session session = sessionFactory.openSession();
        List<Project> projects = session.createCriteria(Project.class).list();
        session.close();
        return projects;
    }

    @Override
    public Project findById(Long id) {
        Session session = sessionFactory.openSession();
        Project project = session.get(Project.class, id);
        session.close();
        return project;
    }
}
