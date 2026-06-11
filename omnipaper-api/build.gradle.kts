plugins {
    `java-library`
    `maven-publish`
}

java {
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    api(project(":paper-api"))
}

publishing {
    publications.create<MavenPublication>("maven") {
        groupId = project.group.toString()
        artifactId = "omnipaper-api"
        version = project.version.toString()
        from(components["java"])
    }
}
