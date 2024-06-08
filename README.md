# BibliAnimé

Bibliothèque pour gérer la liste des animés : leurs avancements de sortie, 
ainsi que celui de visionnage.

# Exécuter l'application

Dans la racine du projet, exécutez la commande suivante dans un terminal.

```cmd
docker compose up --build
```

La première fois que vous construisez et exécutez l'application, Docker télécharge les dépendances et construit l'application. 
Cela peut prendre plusieurs minutes en fonction de votre connexion réseau.

Ouvrez un navigateur et visualisez l'application à l'adresse http://localhost:8080/swagger-ui.html

Dans le terminal, appuyez sur `ctrl+c` pour arrêter l'application.


# Exécuter l'application en arrière-plan

Vous pouvez exécuter l'application détachée du terminal en ajoutant l'option -d. 
Dans la racine du projet, exécutez la commande suivante dans un terminal.

```cmd
docker compose up --build -d
```

Dans le terminal, exécutez la commande suivante pour arrêter l'application.

```cmd
docker compose down
```



## Export data

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

```cmd
docker build -t NAME:TAG .
```

### Build and run depuis docker compose

```cmd
docker compose up --build
```

Your application will be available at http://localhost:8080.

### Deploying your application to the cloud

First, build your image, e.g.: `docker build -t myapp .`.
If your cloud uses a different CPU architecture than your development
machine (e.g., you are on a Mac M1 and your cloud provider is amd64),
you'll want to build the image for that platform, e.g.:
`docker build --platform=linux/amd64 -t myapp .`.

# Contributeur

# Licence

