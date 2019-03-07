package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class taskMapperTestSuite {
    @Test
    public void shouldMapToTask() {

        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "Content");
        TaskMapper taskMapper = new TaskMapper();
        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertEquals(1, task.getId().intValue());
        Assert.assertEquals("title", task.getTitle());
        Assert.assertEquals("Content", task.getContent());
    }

    @Test
    public void shouldMapToTaskDto() {
        //Given
        Task task = new Task(1L, "title", "Content");
        TaskMapper taskMapper = new TaskMapper();
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals(1, taskDto.getId().intValue());
        Assert.assertEquals("title", taskDto.getTitle());
        Assert.assertEquals("Content", taskDto.getContent());
    }

    @Test
    public  void shouldMapToTaskDtoListEmpty() {
        //Given
        List<Task> taskList = new ArrayList<>();
        TaskMapper taskMapper = new TaskMapper();

        //When
        List<TaskDto> list = taskMapper.mapToTaskDtoList(taskList);

        //Then
        Assert.assertEquals(0, list.size());
    }
}
