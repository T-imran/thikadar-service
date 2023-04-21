package com.example.task.service;

import com.example.task.helper.DownloadTaskExcel;
import com.example.task.helper.ImportTaskExcel;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;
import org.apache.poi.xssf.extractor.XSSFExportToXml;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class TaskService {

    public final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Get All Tasks
     */
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    /**
     * Save task to db.
     */
    public Task save(Task project) {
        return taskRepository.save(project);
    }

    /**
     * Find a task by id.
     */
    public Task getById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    /**
     * Delete a task by id.
     */
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getByStatusService(String status){
        return taskRepository.findByStatusContaining(status);
    }

    /**
     *
     *Download excel
     */
    public ByteArrayInputStream getActualTaskData() throws IOException {
        List<Task> allTask = taskRepository.findAll();
        return DownloadTaskExcel.dataToExcel(allTask);
    }

    public void saveExcel(MultipartFile file) throws IOException {
        try {
            List<Task> tasks = ImportTaskExcel.convertExcelToListOfTask(file.getInputStream());
            taskRepository.saveAll(tasks);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
}


