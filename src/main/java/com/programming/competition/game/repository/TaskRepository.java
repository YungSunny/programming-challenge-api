package com.programming.competition.game.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programming.competition.game.enums.ResponseMessages;
import com.programming.competition.game.model.Task;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@Log4j2
@AllArgsConstructor
public class TaskRepository {

    private String tasksPath;

    public List<Task> getAllTasks(){
        List<Task> tasks;
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(Objects.requireNonNull
                    (TaskRepository.class.getClassLoader().getResource(tasksPath)).getFile());
            tasks = mapper.readValue(file,
                    mapper.getTypeFactory().constructCollectionType(List.class, Task.class));
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new ResponseStatusException(SERVICE_UNAVAILABLE, ResponseMessages.TASK_ERROR.getMessage());
        }
        return tasks;
    }

    public List<String> getTaskNames() {
        return getAllTasks().stream()
                .map(Task::getName)
                .collect(Collectors.toList());
    }

}
