package com.programming.competition.game.service;

/**
 * Service for rest operations
 */
public interface RestService<T,U> {

    T executeRequest(U input);
}
