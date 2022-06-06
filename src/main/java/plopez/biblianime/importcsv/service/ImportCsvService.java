package plopez.biblianime.importcsv.service;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plopez.biblianime.anime.entity.Anime;
import plopez.biblianime.anime.entity.Titre;
import plopez.biblianime.anime.service.AnimeService;
import plopez.biblianime.importcsv.entity.AnimeCsv;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportCsvService {

    @Autowired
    AnimeService animeService;

    public void importation() throws IOException {
        String fileName = "C:\\DATA\\Projets\\biblianime\\src\\main\\resources\\TblAnime.csv";
        Charset charset = Charset.forName("windows-1252");
        List<AnimeCsv> datas = new CsvToBeanBuilder(new FileReader(fileName, charset))
                .withSeparator(';')
                .withType(AnimeCsv.class)
                .build()
                .parse();

        datas.forEach(animeCsv -> System.out.println(animeCsv.toString()));

        datas.forEach(
                data -> {
                    Anime anime = new Anime();
                    anime.setId(data.getId());
                    anime.setDateDebut(data.getAjouteLe());
                    anime.setDateFin(data.getModifieLe());

                    List<Titre> titres = data.getTitres().stream().map(dataTitre -> {
                        Titre titre = new Titre();
                        titre.setId_anime(data.getId());
                        titre.setNom(dataTitre);
                        return titre;
                    }).collect(Collectors.toList());


                    anime.setTitres(titres);

                    anime.setStatut(data.getStatut());
                    anime.setNote(data.getNote());
                    anime.setType(data.getType());
                    anime.setNbEpisodeVue(data.getNbEpisodesVue());
                    anime.setNbEpisodeTotal(data.getNbEpisodesTotal());
                    anime.setNautiljon(data.getNautiljon());
                    anime.setWikipedia(data.getWikipedia());
                    anime.setCommentaire(data.getCommentaire());

                    animeService.saveAnime(anime);
                });
    }
}
