package com.programming.competition.game.service.impl;

import com.google.gson.JsonObject;
import com.programming.competition.game.enums.ResponseMessages;
import com.programming.competition.game.model.SolvedTask;
import com.programming.competition.game.service.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
public class JDoodleService implements RestService<HttpResponse<String>, SolvedTask> {

    private final String clientId;

    private final String clientSecret;

    private final String url;

    private final String language;

    private final String versionIndex;

    public JDoodleService(String clientId, String clientSecret, String url, String language, String versionIndex) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.url = url;
        this.language = language;
        this.versionIndex = versionIndex;
    }

    public HttpResponse<String> executeRequest(SolvedTask solvedTask) {
        String preparedRequest = prepareRequest(solvedTask);
        HttpRequest request = setRequest(preparedRequest);

        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.info(e.getMessage());
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR,
                    ResponseMessages.SEND_ERROR.getMessage());
        }
        return response;
    }

    private HttpRequest setRequest(String preparedRequest) {
        return HttpRequest.newBuilder(URI.create(url))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(preparedRequest))
                .build();
    }

    private String prepareRequest(SolvedTask solvedTask) {
        if (solvedTask.getSolution() == null) {
            throw new ResponseStatusException(NOT_FOUND,
                    ResponseMessages.SOLUTION_NOT_FOUND.getMessage());
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("clientId", clientId);
        jsonObject.addProperty("clientSecret", clientSecret);
        jsonObject.addProperty("language", language);
        jsonObject.addProperty("script", solvedTask.getSolution());
        jsonObject.addProperty("versionIndex", versionIndex);
        return jsonObject.toString();
    }
}

