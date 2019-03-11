package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldFetchEmptyTaskList() throws Exception {
        List<Task> taskList = new ArrayList<>();

        when(dbService.getAllTask()).thenReturn(taskList);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchTaskList() throws Exception {
        List<TaskDto>  taskDtos = new ArrayList<>();
        taskDtos.add(new TaskDto(1L, "Element1", "Description1"));
        taskDtos.add(new TaskDto(2L, "Element2", "Description2"));
        //When & Then
        when(taskMapper.mapToTaskDtoList(new ArrayList<>())).thenReturn(taskDtos);

        mockMvc.perform(get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    // Potrzebuję tu Pomocy !!!
    // Za choinke MOCK nie zwraca JSON'a
    @Test
    public void shouldFetchTask() throws Exception {
        Optional<Task> task = Optional.of(new Task(1L, "Title", "Content"));
        Task task1 = new Task(1L, "Title", "Content");
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        //When & Then
        when(dbService.getTask(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task1)).thenReturn(taskDto);

        mockMvc.perform(get("/v1/task/getTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(0)))
                .andDo(print());
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        Task task = new Task(1L, "Title", "Content");
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/v1/task/deleteTask")
                        .param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAddTask() throws Exception {
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        Task task = new Task(1L, "Title", "Content");

        when(taskMapper.mapToTask(Matchers.any(TaskDto.class))).thenReturn(task);

        Gson gson = new Gson();
        String json = gson.toJson(taskDto);

        mockMvc.perform(
                post("/v1/task/createTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
        .andExpect(status().isOk());
    }

    // Potrzebuję tu Pomocy !!!
    // Za choinke MOCK nie zwraca JSON'a
    @Test
    public void shouldUpdateTask() throws Exception {
        TaskDto taskDto  = new TaskDto(1L, "Title", "Content");
        Task task = new Task(1L, "Title", "Content");

//        when(taskMapper.mapToTask(Matchers.any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(Matchers.any(Task.class))).thenReturn(task);
        Gson gson = new Gson();
        String json = gson.toJson(taskDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/v1/task/updateTask")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                // to tylko do pokazania ze nie ma Body
//                .andExpect(status().isOk());
                .andExpect(status().isCreated());
    }

}