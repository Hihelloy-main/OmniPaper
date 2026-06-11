plugins {
    `java-library`
    `maven-publish`
}

java {
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenLocal()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    api("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
}

publishing {
    publications.create<MavenPublication>("maven") {
        groupId = project.group.toString()
        artifactId = "omnipaper-api"
        version = project.version.toString()
        from(components["java"])
    }
}