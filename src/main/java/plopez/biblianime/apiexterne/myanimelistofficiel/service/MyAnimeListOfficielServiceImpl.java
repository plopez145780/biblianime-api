package plopez.biblianime.apiexterne.myanimelistofficiel.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plopez.biblianime.apiexterne.myanimelistofficiel.dto.AnimeDTO;
import plopez.biblianime.apiexterne.myanimelistofficiel.dto.AnimePagingDTO;
import plopez.biblianime.apiexterne.myanimelistofficiel.dto.NodeAnimeDTO;
import plopez.biblianime.apiexterne.myanimelistofficiel.provider.MyAnimeListOfficielProvider;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class MyAnimeListOfficielServiceImpl implements MyAnimeListOfficielService {

    Logger log = LoggerFactory.getLogger(MyAnimeListOfficielServiceImpl.class);

    @Autowired
    MyAnimeListOfficielProvider myAnimeListOfficielProvider;

    @Override
    public List<AnimeDTO> getAnimeList(String query, Integer limit, Integer offset, String fields) {

        String fieldsOrDefault = Optional.ofNullable(fields).orElse("id,title,main_picture,sysnopsis,media_type,genres,num_episodes");

        List<AnimeDTO> animesDTO = null;
        HttpResponse<String> httpResponse = myAnimeListOfficielProvider.getAnimeList(query, limit, offset, fieldsOrDefault);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION.mappedFeature(), true);
        try {
            AnimePagingDTO animePagingDTO = objectMapper.readValue(httpResponse.body(), new TypeReference<AnimePagingDTO>() {});
            animesDTO = animePagingDTO.getData().stream().map(NodeAnimeDTO::getNode).collect(toList());
        } catch (JsonProcessingException e) {
            log.error("Deserialization error: {}", e.getMessage());
        }
        return animesDTO;
    }

    @Override
    public AnimeDTO getAnimeDetails(Integer animeId, String fields) {

        String fieldsOrDefault = Optional.ofNullable(fields).orElse("id,title,main_picture,alternative_titles," +
                        "start_date,end_date,synopsis,mean,rank,popularity,nsfw,created_at,updated_at," +
                        "media_type,status,genres,num_episodes,start_season,broadcast,source," +
                        "average_episode_duration,rating,pictures,background,studios");

        AnimeDTO animeDTO = null;
        HttpResponse<String> httpResponse = myAnimeListOfficielProvider.getAnimeDetails(animeId, fieldsOrDefault);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION.mappedFeature(), true);

        try {
            animeDTO = objectMapper.readValue(httpResponse.body(), new TypeReference<AnimeDTO>() {
            });
        } catch (JsonProcessingException e) {
            log.error("Deserialization error: {}", e.getMessage());
        }
        return animeDTO;
    }

    @Override
    public List<AnimeDTO> getAnimeRanking(String rankingType, Integer limit, Integer offset, String fields) {

        String fieldsOrDefault = Optional.ofNullable(fields).orElse("id,title,main_picture,sysnopsis,media_type,genres,num_episodes");

        List<AnimeDTO> animesDTO = null;
        HttpResponse<String> httpResponse = myAnimeListOfficielProvider.getAnimeRanking(rankingType, limit, offset, fieldsOrDefault);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION.mappedFeature(), true);
        try {
            AnimePagingDTO animePagingDTO = objectMapper.readValue(httpResponse.body(), new TypeReference<AnimePagingDTO>() {});
            animesDTO = animePagingDTO.getData().stream().map(NodeAnimeDTO::getNode).collect(toList());
        } catch (JsonProcessingException e) {
            log.error("Deserialization error: {}", e.getMessage());
        }
        return animesDTO;
    }

    @Override
    public List<AnimeDTO> getSeasonalAnime(Integer year, String season, String sort, Integer limit, Integer offset, String fields) {

        String fieldsOrDefault = Optional.ofNullable(fields).orElse("id,title,main_picture,sysnopsis,media_type,genres,num_episodes");
        Integer limitOrDefault = Optional.ofNullable(limit).orElse(300);

        List<AnimeDTO> animesDTO = null;
        HttpResponse<String> httpResponse = myAnimeListOfficielProvider.getSeasonalAnime(year, season, sort, limitOrDefault, offset, fieldsOrDefault);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION.mappedFeature(), true);
        try {
            AnimePagingDTO animePagingDTO = objectMapper.readValue(httpResponse.body(), new TypeReference<AnimePagingDTO>() {});
            animesDTO = animePagingDTO.getData().stream().map(NodeAnimeDTO::getNode).collect(toList());
        } catch (JsonProcessingException e) {
            log.error("Deserialization error: {}", e.getMessage());
        }
        return animesDTO;
    }
}
