package com.programming.competition.game.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.programming.competition.game.enums.ResponseMessages;
import com.programming.competition.game.model.JDoodleResponse;
import com.programming.competition.game.model.Player;
import com.programming.competition.game.model.SolvedTask;
import com.programming.competition.game.model.Task;
import com.programming.competition.game.model.TaskSubmitResponse;
import com.programming.competition.game.repository.TaskRepository;
import com.programming.competition.game.service.PlayerService;
import com.programming.competition.game.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService<Task,SolvedTask> {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PlayerService<Player> playerService;

    @Autowired
    private JDoodleService jDoodleService;

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public Task getTask(String taskName) {
        return taskRepository.getAllTasks().stream()
                .filter(task -> task.getName().equals(taskName))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Task with name %s does not exist. Please choose from available tasks %s",
                        taskName, taskRepository.getTaskNames())));
    }

    public TaskSubmitResponse submitTask(SolvedTask solvedTask) {
        ObjectMapper mapper = new ObjectMapper();
        String response = jDoodleService.executeRequest(solvedTask).body();
        JDoodleResponse jDoodleResponse;
        try {
            jDoodleResponse = mapper.readValue(response, JDoodleResponse.class);
        } catch (JsonProcessingException e) {
            log.info(e.getMessage());
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, ResponseMessages.PARSING_ERROR.getMessage());
        }
        return isTaskDoneCorrectly(jDoodleResponse, solvedTask);
    }

    private TaskSubmitResponse isTaskDoneCorrectly(JDoodleResponse jDoodleResponse, SolvedTask solvedTask) {
        if (checkSolution(jDoodleResponse.getOutput(), solvedTask.getName())) {
            playerService.addPlayerScore(solvedTask.getPlayerName(), solvedTask.getName());
            return new TaskSubmitResponse(jDoodleResponse.getStatusCode(),
                    ResponseMessages.CORRECT.getMessage());
        }
        return new TaskSubmitResponse(jDoodleResponse.getStatusCode(),
                ResponseMessages.INCORRECT.getMessage());
    }

    private boolean checkSolution(String output, String taskName) {
        Task checkedTask = getTask(taskName);
        return checkedTask.getAnswer().equals(output.trim());
    }
}
