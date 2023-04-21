package com.example.task.service;

import com.example.task.model.Project;
import com.example.task.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    public final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * Get All projects
     */
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    /**
     * Save project to db.
     */
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    /**
     * Find a project by id.
     */
    public Project getById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    /**
     * Delete a post by id.
     */
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }




}
