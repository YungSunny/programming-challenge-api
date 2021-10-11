package com.programming.competition.game.service;

import com.programming.competition.game.model.Task;
import com.programming.competition.game.repository.TaskRepository;
import com.programming.competition.game.service.impl.JDoodleService;
import com.programming.competition.game.service.impl.PlayerServiceImpl;
import com.programming.competition.game.service.impl.TaskServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private PlayerServiceImpl playerServiceImpl;

    @Mock
    private JDoodleService jDoodleService;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    public void shouldGetAllTasks() {

        //when
        taskService.getAllTasks();

        //then
        verify(taskRepository).getAllTasks();
    }

    @Test
    public void shouldReturnTask() {

        //given
        Task task = new Task("name", "test", "0");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(taskRepository.getAllTasks()).thenReturn(taskList);

        //when
        Task returnedTask = taskService.getTask("name");

        //then
        assertEquals(task, returnedTask);
    }

    @Test
    public void shouldThrowErrorIfTaskNotExist() {

        //given
        List<Task> taskList = new ArrayList<>();
        List<String> taskNames = new ArrayList<>();
        Task task = new Task("name", "test", "0");
        taskList.add(task);
        taskNames.add(task.getName());
        when(taskRepository.getAllTasks()).thenReturn(taskList);
        when(taskRepository.getTaskNames()).thenReturn(taskNames);
        String notExistingName = "test";

        //when
        try {
            taskService.getTask(notExistingName);
        }

        //then
        catch (ResponseStatusException e) {
            assertEquals(e.getStatus(), HttpStatus.NOT_FOUND);
            assertEquals(e.getReason(), String.format("Task with name %s does not exist. Please choose from available tasks %s",
                    notExistingName, taskNames));
        }
    }
}
