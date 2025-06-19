# Backend Icar

[![Java](https://img.shields.io/badge/Java-21-007396?logo=java&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-0DB33F?logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-6DB33F?logo=spring-security&logoColor=white)](https://spring.io/projects/spring-security)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data-JPA-6DB33F?logo=spring&logoColor=white)](https://spring.io/projects/spring-data-jpa)
[![H2 Database](https://img.shields.io/badge/H2-Database-0000FF?logo=h2-database&logoColor=white)](https://www.h2database.com/)
[![MariaDB Database](https://img.shields.io/badge/MariaDB-11.6.2-003545?logo=mariadb&logoColor=white)](https://mariadb.org/)
[![JUnit](https://img.shields.io/badge/JUnit-5.9.3-25A162?logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![Maven](https://img.shields.io/badge/Maven-3.8.6-C71A36?logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.24-94F481?logo=lombok&logoColor=white)](https://projectlombok.org/)
[![BCrypt](https://img.shields.io/badge/BCrypt-4.0.0-FF9800?logo=java&logoColor=white)](https://www.mindrot.org/projects/jbcrypt/)
[![Jenkins](https://img.shields.io/badge/Jenkins-2.504.2-D24939?logo=jenkins&logoColor=white)](https://www.jenkins.io/)
[![MIT License](https://img.shields.io/badge/Licence-MIT-red.svg)](licence)

## 📋 Description

API backend REST pour la plateforme Icar, offrant gestion sécurisée des utilisateurs, véhicules et covoiturages.

## 🏗️ Architecture

L'application suit une architecture en couches :

```
xyz.dedsecm.icar/
├── config/          # Configuration Spring et sécurité
├── controller/      # Points d'entrée REST API
├── dto/             # Objets de transfert de données
├── exception/       # Gestion centralisée des erreurs
├── mapper/          # Conversion entre entités et DTOs
├── model/           # Entités JPA et modèles de domaine
├── repository/      # Accès aux données
├── service/         # Logique métier
└── VroomvroomcarApplication.java  # Point d'entrée
```

## 🔑 Fonctionnalités principales

- **Gestion des utilisateurs**
    - Inscription et authentification
    - Profils avec rôles (USER, ADMIN)
    - Système de bannissement temporaire
    - Association avec un véhicule personnel

- **Gestion des véhicules**
    - Enregistrement avec caractéristiques complètes
    - Attribution aux utilisateurs

- **Système de covoiturage**
    - Création et recherche de trajets
    - Réservation de places
    - Statuts (RESERVABLE, EN_COURS, ANNULE)
    - Calcul automatique des durées et distances

- **Sécurité**
    - Authentification JWT
    - Contrôle d'accès basé sur les rôles
    - Protection des endpoints sensibles

## 🛠️ Technologies utilisées

- **Spring Boot** - Framework d'application
- **Spring Security** - Authentification et autorisation
- **Spring Data JPA** - Persistance des données
- **H2 Database** - Base de données embarquée
- **MariaDB** - Base de données relationnelle
- **JWT** - Authentification par token
- **Lombok** - Réduction de code boilerplate
- **BCrypt** - Hachage sécurisé des mots de passe

## 🚀 Installation et démarrage

### Prérequis
- Java 17+
- Maven 3.6+

### Commandes

```bash
# Cloner le dépôt
git clone https://github.com/Icar-d062025/backend.git
cd backend

# Compiler le projet
mvn clean install

# Démarrer l'application
mvn spring-boot:run
```

L'API est accessible à l'adresse : http://localhost:8080
Console H2 (dev uniquement) : http://localhost:8080/h2-console

## 📡 Points d'entrée API

### Authentification
- `POST /api/auth/login` - Connexion et obtention du token JWT

### Utilisateurs
- `GET /api/users` - Liste des utilisateurs
- `GET /api/users/{id}` - Détails d'un utilisateur
- `GET /api/users/email/{email}` - Recherche par email
- `GET /api/users/username/{username}` - Recherche par nom d'utilisateur
- `POST /api/users` - Création d'un utilisateur
- `PUT /api/users/{id}` - Mise à jour d'un utilisateur
- `DELETE /api/users/{id}` - Suppression d'un utilisateur
- `POST /api/users/{id}/ban?raison=...&duree=...` - Bannissement temporaire
- `POST /api/users/{id}/unban` - Débannir un utilisateur
- `POST /api/users/{userId}/assign-vehicle/{vehicleId}` - Attribution d'un véhicule

### Covoiturages
- `GET /api/covoiturages` - Liste des covoiturages
- `POST /api/covoiturages` - Création d'une offre de covoiturage

### Administration
- `GET /api/admin/hello` - Test d'accès administrateur

## 🧪 Tests

```bash
# Exécuter les tests unitaires
mvn test

# Exécuter les tests d'intégration
mvn verify
```

## 👥 Contributeurs

Ce projet est développé dans le cadre d'un projet fil rouge par l'équipe Icar, avec les contributions de :
- [RikiLaNeko](https://github.com/RikiLaNeko)
- [Fromont-L](https://github.com/Fromont-L)
- [p243k](https://github.com/p243k)

## 📄 Licence

Ce projet est sous licence [MIT](licence)
