package com.programming.competition.game.service.config;

import com.programming.competition.game.service.impl.JDoodleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public JDoodleService jDoodleService(@Value("${jdoodle.clientId}") String clientId,
                                    @Value("${jdoodle.clientSecret}") String clientSecret,
                                    @Value("${jdoodle.url}") String url,
                                    @Value("${jdoodle.language}") String language,
                                    @Value("${jdoodle.version.index}") String versionIndex) {
        return new JDoodleService(clientId, clientSecret, url, language, versionIndex);
    }
}
