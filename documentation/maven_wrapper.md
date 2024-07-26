# Maven Wrapper

# Définition

Le Maven Wrapper permet de fixer une version spécifique de Maven pour le projet.
L'ensemble des contributeurs n'ont pas besoins d'installer Maven
(à par pour certaines situations spécifiques)
(a par configurer l'IDE pour utiliser le wrapper).
Cela simplifie l'installation d'environnement du contributeur.
Homogénéise les environnements entre les contributeurs.
Homogénéise les environnements entre les contributeurs et environnement de production.
Cela permet aussi d'avoir la même version dans l'environnement de build dans la pipeline.

# Installation de Maven Wrapper dans un projet

Il existe deux façons de le configurer dans un projet, où le plus simple est d'utiliser un plugin approprié pour 
l'automatiser ou en appliquant l'installation manuelle.

## Plugin Maven Wrapper

L'installation et la mise à jour du Wrapper peut être fait avec le [plugin Maven Wrapper](https://maven.apache.org/wrapper/maven-wrapper-plugin/).
Il faut pour cela un maven fonctionnel quelle que soit la version 
(installer en local sur le PC, ou celui de l'IDE, ou le Maven Wrapper versionné dans le projet)

Tout d'abord, nous devons aller dans le dossier principal du projet et exécuter cette commande :

```mvn -N wrapper:wrapper```

On peut aussi spécifier la version de Maven :

```mvn -N wrapper:wrapper -Dmaven=3.5.2```

La version peut être configurée dans le pom.xml

L'option -N signifie non-récursif de sorte que le Wrapper ne sera appliqué qu'au projet principal du répertoire actuel,
pas dans aucun sous-module.

Après avoir exécuté l'objectif, nous aurons plus de fichiers et de répertoires dans le projet :

    mvnw: c'est un script shell exécutable Unix utilisé à la place d'un Maven entièrement installé.
    mvnw.cmd : c'est la version Batch du script ci-dessus.
    .mvn : le dossier caché qui détient la bibliothèque Maven Wrapper Java et son fichier de propriétés

## Installation manuelle

Avec une approche manuelle, nous pouvons copier des fichiers et des dossiers vus ci-dessus d'un autre projet au dossier
principal du projet en cours.

Ensuite, nous devons spécifier la version de Maven à utiliser dans le fichier de propriétés d'emballage situé dans le
fichier .mvn/wrapper/maven-wrapper.properties.

Par exemple, notre fichier de propriétés a la ligne suivante :

distributionUrl=https://repo1.maven.org/maven2/org/apache/maven/apache-maven/3.5.2/apache-maven-3.5.2-bin.zip

Par conséquent, la version 3.5.2 sera téléchargée et utilisée.

# Utilisation

Après cela, nous pouvons exécuter nos objectifs comme celui-ci pour le système Unix :

```./mvnw clean install```

Et la commande suivante pour Windows:

```mvnw.cmd clean install```

Si nous n'avons pas le Maven spécifié dans les propriétés du Wrapper,
il sera téléchargé et installé dans le dossier 'USER'HOME/.m2/wrapper/dists du système.

Executons notre projet Spring-Boot :

```./mvnw spring-boot:run```

La sortie est la même que pour un Maven entièrement installé.

Remarque : nous utilisons le mvnw exécutable à la place de mvn, qui est maintenant le programme de ligne de commande
Maven.


