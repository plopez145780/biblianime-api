package plopez.biblianime.apiexterne.myanimelistofficiel.config;

import lombok.Data;

@Deprecated
@Data
//@Configuration
//@ConfigurationProperties(prefix = "api.myanimelistofficiel")
public class MyAnimeListOfficielConfig {

    private final String titre = "MyAnimeListOfficiel";

    private final int requestLimite = 1000000;

    //@Value("${base-url}")
    private String baseUrl;

    //@Value("${client-id}")
    private String clientId;
}
