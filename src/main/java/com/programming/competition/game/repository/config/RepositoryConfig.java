package com.programming.competition.game.repository.config;

import com.programming.competition.game.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public TaskRepository taskRepository(@Value("${tasks.path}") String tasksPath) {
        return new TaskRepository(tasksPath);
    }
}
