package plopez.biblianime.myanimelist.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class RequeteExterne {

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
