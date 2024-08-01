package plopez.biblianime.apiexterne.myanimelistofficiel.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Deprecated
@Data
@Configuration
@ConfigurationProperties(prefix = "api.myanimelistofficiel")
public class MyAnimeListOfficielConfig {

    private final String titre = "MyAnimeListOfficiel";

    private final int requestLimite = 1000000;

    @Value("${base-url}")
    private String baseUrl;

    @Value("${client-id}")
    private String clientId;
}
