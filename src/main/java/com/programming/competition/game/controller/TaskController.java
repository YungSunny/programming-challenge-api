package com.programming.competition.game.controller;

import com.programming.competition.game.model.SolvedTask;
import com.programming.competition.game.model.Task;
import com.programming.competition.game.model.TaskSubmitResponse;
import com.programming.competition.game.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/challenge")
public class TaskController {

    @Autowired
    private TaskService<Task,SolvedTask> taskService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<Task> getTask(@PathVariable String name) {
        return new ResponseEntity<>(taskService.getTask(name), HttpStatus.OK);
    }

    @PostMapping("/submitTask")
    public TaskSubmitResponse submitTask(@RequestBody SolvedTask solvedTask) {
        return taskService.submitTask(solvedTask);
    }
}
