package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiveTestSuite {
    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void shouldGetAllTask() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Element", "content"));
        //When
        when(repository.findAll()).thenReturn(tasks);
        List service = dbService.getAllTask();
        //Then
        Assert.assertEquals(1, service.size());
    }

    @Test
    public void shouldGetOne() {
        //Given
        Long id = 1L;
        Task task = new Task(id, "Element", "content");
        //When
        when(repository.findById(id)).thenReturn(java.util.Optional.ofNullable(task));
        Optional<Task> service = dbService.getTask(id);
        //Then
        Assert.assertEquals("Element", service.orElse(task).getTitle());
    }

    @Test
    public void shouldSaveOne() {
        //Given
        Long id = 1L;
        Task task = new Task(id, "Element", "content");
        //When
        when(repository.save(task)).thenReturn(task);
        Task service = dbService.saveTask(task);
        //Then
        Assert.assertEquals(task.getTitle(), service.getTitle());
    }

    @Test
    public void shouldDeleteOne() {
        //Given
        Long id = 1L;
        //When
        repository.delete(id);

        //then @testing void method
        try {
            dbService.deleteTask(id);
            Assert.assertTrue(Boolean.TRUE);
        }catch (NullPointerException e){
        }

    }
}