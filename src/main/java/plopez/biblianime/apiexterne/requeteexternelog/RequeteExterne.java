package plopez.biblianime.apiexterne.requeteexternelog;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class RequeteExterne {

    public RequeteExterne(ProviderExterne provider, String url) {
        this.provider = provider;
        this.date = LocalDateTime.now();
        this.url = url;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime date;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProviderExterne provider;

    @NotNull
    private String url;
}
