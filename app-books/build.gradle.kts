plugins {
    id("java")

    //Requiere la version
    id("io.quarkus") version "3.11.1"
}

group = "com.distribuida"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
val quarkusVersion = "3.11.1"
val quarkusPlatformArtifactId= "quarkus-bom"
dependencies {

    //implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))

    implementation(enforcedPlatform("io.quarkus:${quarkusPlatformArtifactId}:${quarkusVersion}"))
    implementation("io.quarkus:quarkus-arc") //CDI
    //Rest motor
    //implementation("io.quarkus:quarkus-rest")
    //JackRS reactivo
    implementation("io.quarkus:quarkus-resteasy-reactive") //REST
    //Manejo de Json
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson") //JSON
    //Repositories
    implementation("io.quarkus:quarkus-hibernate-orm-panache") //JPA+HIBERNATE

    implementation("org.postgresql:postgresql:42.7.3") //Database
    //implementation("com.h2database:h2:2.2.224")
    compileOnly("org.projectlombok:lombok:1.18.30")

    implementation("io.quarkus:quarkus-rest-client-reactive") //REST
    //Manejo de Json
    implementation("io.quarkus:quarkus-rest-client-reactive-jackson") //JSON
}

tasks.test {
    useJUnitPlatform()
}