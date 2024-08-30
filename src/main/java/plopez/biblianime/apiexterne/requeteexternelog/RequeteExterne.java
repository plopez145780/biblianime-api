package plopez.biblianime.apiexterne.requeteexternelog;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class RequeteExterne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime date = LocalDateTime.now();

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProviderExterne provider;

    @NotNull
    private String url;

    public RequeteExterne(ProviderExterne provider, String url) {
        this.provider = provider;
        this.url = url;
    }
}
