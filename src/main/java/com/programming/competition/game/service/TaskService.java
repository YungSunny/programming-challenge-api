package com.programming.competition.game.service;

import java.util.List;

/**
 * Service for challenge task related operations
 */
public interface TaskService<U,K,Z> {

    /**
     * Gets all available tasks
     *
     * @return all the tasks
     */
    List<U> getAllTasks();

    /**
     * Gets a specific task
     *
     * @param taskName name of the task you want to get
     */
    U getTask(String taskName);

    /**
     * Gets a specific task
     *
     * @param solvedTask completed task that is ready for the calculation of the result
     *
     * @return formulated response according to the result of given task
     */
    K submitTask(Z solvedTask);
}
