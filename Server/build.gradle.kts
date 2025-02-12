/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */

/**
 * Ce fichier est le fichier de configuration de Gradle pour le projet Spring Boot.
 * Il permet de définir les dépendances du projet, la version de Java utilisée, etc.
 */

/* Déclaration des plugins utilisés */
plugins {
    id("java") // Plugin Java
    id("org.springframework.boot") version "3.2.3" // Plugin Spring Boot
}

group = "fr.s401_equipe3b" // Groupe du projet
version = "1.0-SNAPSHOT" // Version du projet

/* Dépôts utilisés par le projet */
repositories {
    mavenCentral() // Dépôt Maven Central
}

/* Dépendances du projet */
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.3") // Dépendance Spring Boot pour le Web
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:3.2.3") // Dépendance Spring Boot pour MongoDB

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.4.0") // Dépendance Springdoc OpenAPI pour la documentation

    testImplementation(platform("org.junit:junit-bom:5.9.1")) // Dépendance JUnit
    testImplementation("org.junit.jupiter:junit-jupiter") // Dépendance JUnit Jupiter
}

/* Configuration de la tâche Gradle pour les tests */
tasks.test {
    useJUnitPlatform()
}

/* Configuration de la tâche Gradle pour la compilation Java */
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8" // Encodage des fichiers Java
    options.isIncremental = true // Compilation incrémentale (plus rapide grâce au cache)
    options.compilerArgs.add("-parameters") // Ajout des paramètres de compilation pour Spring Boot
    sourceCompatibility = "17" // Version de compatibilité
}
