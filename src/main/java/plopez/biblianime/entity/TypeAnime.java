package plopez.biblianime.entity;

public enum TypeAnime {
    SERIE("Série"),
    FILM("Film"),
    OAV("OAV"),
    SPECIAL("Spécial"),
    ONA("ONA");

    private final String nom;

    TypeAnime(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}
