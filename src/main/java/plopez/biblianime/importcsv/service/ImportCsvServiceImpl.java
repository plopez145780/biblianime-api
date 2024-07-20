package plopez.biblianime.importcsv.service;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import plopez.biblianime.anime.entity.Anime;
import plopez.biblianime.anime.entity.AnimeTitle;
import plopez.biblianime.anime.service.AnimeService;
import plopez.biblianime.anime.service.TitleAnimeService;
import plopez.biblianime.importcsv.bean.AnimeCsv;
import plopez.biblianime.importcsv.converter.AnimeConverter;
import plopez.biblianime.importcsv.converter.AnimeTitleConverter;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportCsvServiceImpl implements ImportCsvService {

    @Autowired
    AnimeService animeService;
    @Autowired
    TitleAnimeService titleAnimeService;

    @Value("${app.importcsv.source}")
    String fileName;

    public void importation() throws IOException {

        System.out.println("fileName:" + fileName);

        Charset charset = Charset.forName("windows-1252");
        List<AnimeCsv> datas = new CsvToBeanBuilder(new FileReader(fileName, charset))
                .withSeparator(';')
                .withType(AnimeCsv.class)
                .build()
                .parse();

        datas.forEach(
                data -> {
                    System.out.println(data.toString());

                    // Crée et sauvegarde les titres
                    List<AnimeTitle> animeTitles = data.getTitres().stream().map(dataTitre -> {
                        AnimeTitle animeTitle = AnimeTitleConverter.convert(dataTitre, data.getId());
                        titleAnimeService.save(animeTitle);

                        return animeTitle;
                    }).collect(Collectors.toList());

                    // Crée et sauvegarde l'animé
                    Anime anime = AnimeConverter.convert(data, animeTitles);
                    animeService.save(anime);
                });
    }
}
