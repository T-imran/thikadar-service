package com.example.task.controller;

import com.example.task.helper.ImportTaskExcel;
import com.example.task.model.Task;
import com.example.task.service.TaskService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Get All created projects
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        try {
            List<Task> projects = taskService.getAll();
            return ResponseEntity.ok(projects);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
        }
    }

    /**
     * Save a project.
     */
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Task task) {
        try {
            return ResponseEntity.ok(taskService.save(task));
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
            return ResponseEntity.ok(taskService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
        }
    }

    @GetMapping("/get-by-status")
    public ResponseEntity<?> getByStatus(@RequestParam String status) {
        try {
            List<Task> getByStatus = taskService.getByStatusService(status);
            return ResponseEntity.ok(getByStatus);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
        }
    }

    /**
     * Delete a post by ID.
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(@RequestParam Long id) throws java.io.IOException {
        try {
            taskService.deleteById(id);
            return ResponseEntity.ok(Collections.singletonMap("Message", "Project deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
        }
    }

    /**
     * Download task Excel
     *
     */
    @RequestMapping("/download-task-excel")
    public ResponseEntity<Resource> downloadExcel() throws IOException {
        String filename = "Task_List.xlsx";

        ByteArrayInputStream actualTaskData = taskService.getActualTaskData();
        InputStreamResource file= new InputStreamResource(actualTaskData);

        ResponseEntity<Resource> body =  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename"+filename)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(file);
        return body;
    }

    @PostMapping("/upload-task-excel")
    public ResponseEntity<?> uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {
        if(ImportTaskExcel.checkExcelFormat(file)){
            taskService.saveExcel(file);
            return ResponseEntity.ok(Map.of("massage", "File uploaded successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload only excel file");
    }

}
