package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DbService {
    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTask() {
        return repository.findAll();
    }

    public Task getTaskById(long id) {
        return repository.findById(id);
    }

    public boolean existsTaskById(long id) {
        return repository.existsById(id);
    }

}
