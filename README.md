# BibliAnimé

Bibliothèque pour gérer la liste des animés : leurs avancements de sortie, 
ainsi que celui de visionnage.



# Exécuter l'application (production)

Registre Docker Hub de l'image de l'application : https://hub.docker.com/r/plopez832/biblianime

## Exemple : depuis un serveur docker engine

### Réseau

```bash
 docker network create biblianime_network
```

### BDD

#### Créer un volume nommé

```bash
 docker volume create biblianime_db-data
```

#### Démarrer le service

```bash
 docker run -ti --name biblianime_db_1 \
  --network biblianime_network \
  -v biblianime_db-data:/var/lib/postgresql/data \
  -e POSTGRES_DB=biblidb \
  -e POSTGRES_USER=bibliuser \
  -e POSTGRES_PASSWORD=biblipass \
  -d postgres:16.3-alpine3.20
```

 ### PGADMIN

#### Créer un volume nommé

```bash
 docker volume create biblianime_pgadmin-data
```

#### Démarrer le service

```bash
 docker run --name biblianime_pgadmin_1 \
  --network biblianime_network \
  -P \
  -v biblianime_pgadmin-data:/var/lib/pgadmin \
  -e PGADMIN_DEFAULT_EMAIL=pgadmin@localhost.fr \
  -e PGADMIN_DEFAULT_PASSWORD=pgadminpass \
  -d dpage/pgadmin4:8
```

### Biblianimé

#### Récupérer l'image

```bash
 docker pull plopez832/biblianime
```

#### Démarrer le service

```bash
 docker run --name biblianime \
  --network biblinetwork \
  -p 8080:8080 \
  -e POSTGRES_URL=jdbc:postgresql://biblianime_db_1:5432/biblidb \
  -e POSTGRES_DB=biblidb \
  -e POSTGRES_USER=bibliuser \
  -e POSTGRES_PASSWORD=biblipass \
  -d plopez832/biblianime
```

---

# Variable d'environnement et secret

## Variables d'environnement

- Créer à la racine du projet, un fichier ".env" à partir du template [.env.template](..%2F.env.template)
- Renseigner les différentes informations
- Le mot de passe postgres est actuellement transmis par une variable d'environnement.

## Secret Postgres (inutiliser pour le moment)

- Créer dans le dossier "config", un fichier "db-password.txt" à partir du template [db-password.txt.template](db-password.txt.template)
- Renseigner le mot de passe d'accès à la BDD postgresql

## Secret Pgadmin

- Créer dans le dossier "config", un fichier "pgadmin-password.txt" à partir du template [pgadmin-password.txt.template](pgadmin-password.txt.template)
- Renseigner le mot de passe d'accès du dashboard de pgadmin

---

# Exécuter l'application (développement)

Dans la racine du projet, exécutez la commande suivante dans un terminal.

```bash
docker compose up --build
```

La première fois que vous construisez et exécutez l'application, Docker télécharge les dépendances et construit l'application. 
Cela peut prendre plusieurs minutes en fonction de votre connexion réseau.

Ouvrez un navigateur et visualisez l'application à l'adresse http://localhost:8080/swagger-ui.html

Dans le terminal, appuyez sur `ctrl+c` pour arrêter l'application.


## Exécuter l'application en arrière-plan

Vous pouvez exécuter l'application détachée du terminal en ajoutant l'option -d. 
Dans la racine du projet, exécutez la commande suivante dans un terminal.

```bash
docker compose up --build -d
```

Dans le terminal, exécutez la commande suivante pour arrêter l'application.

```bash
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

### À partir du dockerfile

Créez l'image

```bash
docker build -t NAME:TAG .
```

### Build and run depuis docker compose

```bash
docker compose up --build
```

# Contributeur

- plopez145780

# Licence

