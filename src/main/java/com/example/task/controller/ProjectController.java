package com.example.task.controller;

import com.example.task.model.Project;
import com.example.task.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * Get All created projects
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        try {
            List<Project> projects = projectService.getAll();
            return ResponseEntity.ok(projects);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
        }
    }

    /**
     * Save a project.
     */
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Project project) {
        try {
            return ResponseEntity.ok(projectService.save(project));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
        }
    }

    /**
     * Get an action by ID.
     */
    @GetMapping("/get-by-id")
    public ResponseEntity<?> getById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(projectService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
        }
    }

    /**
     * Delete a post by ID.
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(@RequestParam Long id) {
        try {
            projectService.deleteById(id);
            return ResponseEntity.ok(Collections.singletonMap("Message", "Project deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
        }
    }
}
