package plopez.biblianime.importcsv.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import plopez.biblianime.importcsv.service.ImportCsvService;

import java.io.IOException;

@Tag(name = "import", description = "API d'importation de données'")
@RequestMapping("/import")
@Controller
public class ImportCsvController {

    @Autowired
    private ImportCsvService importCsvService;

    @Operation(summary = "Ajouter des données depuis un CSV", description = "Ajouter des données depuis un CSV")
    @PostMapping("/")
    public String importCsv(

            /*@RequestBody(
                    content = @Content(
                            mediaType = "text/csv",
                            schema = @Schema(type = "string", format = "binary")
                    )
            )
            @Valid
            MultipartFile file*/) {
        try {
//            System.out.printf(file.getName());
            importCsvService.importation();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Importation avec succès";
    }
}
