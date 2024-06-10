# BibliAnimé

Bibliothèque pour gérer la liste des animés : leurs avancements de sortie, 
ainsi que celui de visionnage.

# Exécuter l'application (production)

Registre Docker Hub : https://hub.docker.com/r/plopez832/biblianime

```cmd
docker pull plopez832/biblianime
docker run --name biblianime -p 8080:8080 plopez832/biblianime
```

# Exécuter l'application (développement)

Dans la racine du projet, exécutez la commande suivante dans un terminal.

```cmd
docker compose up --build
```

La première fois que vous construisez et exécutez l'application, Docker télécharge les dépendances et construit l'application. 
Cela peut prendre plusieurs minutes en fonction de votre connexion réseau.

Ouvrez un navigateur et visualisez l'application à l'adresse http://localhost:8080/swagger-ui.html

Dans le terminal, appuyez sur `ctrl+c` pour arrêter l'application.


## Exécuter l'application en arrière-plan

Vous pouvez exécuter l'application détachée du terminal en ajoutant l'option -d. 
Dans la racine du projet, exécutez la commande suivante dans un terminal.

```cmd
docker compose up --build -d
```

Dans le terminal, exécutez la commande suivante pour arrêter l'application.

```cmd
docker compose down
```

# Export data

- Dans access, ouvrir le formulaire "Liste d'animés"
- Dans le menu, "Données externes" > Exporter > Excel.
- Supprimer la colonne "ouvrir"
- Dans excel, Fichier > Enregistrer sous
- Format CSV avec point virgule

## Swagger

### URL

- http://localhost:8080/swagger-ui.html
- http://localhost:8080/swagger-ui/index.html

## Build

### A partir du dockerfile

créez l'image

```cmd
docker build -t NAME:TAG .
```

### Build and run depuis docker compose

```cmd
docker compose up --build
```

# Contributeur

# Licence

