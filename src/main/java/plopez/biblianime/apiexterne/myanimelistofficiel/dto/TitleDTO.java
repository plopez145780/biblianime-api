package plopez.biblianime.apiexterne.myanimelistofficiel.dto;

import lombok.Data;

import java.util.List;

@Data
public class TitleDTO {

    private List<String> synonyms;
    private String en;
    private String ja;
}
