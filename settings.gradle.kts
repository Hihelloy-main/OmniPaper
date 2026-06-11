import java.util.Locale

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

if (!file(".git").exists()) {
    val errorText = """
        
        =====================[ ERROR ]=====================
         The OmniPaper project directory is not a properly cloned Git repository.
         
         In order to build OmniPaper from source you must clone
         the OmniPaper repository using Git, not download a code
         zip from GitHub.
         
         Built OmniPaper jars are available for download at
         https://github.com/OmniPaperMC/OmniPaper/releases
         
         See CONTRIBUTING.md for information on building and
         modifying OmniPaper.
        ===================================================
    """.trimIndent()
    error(errorText)
}

rootProject.name = "omnipaper"

for (name in listOf("omnipaper-api", "omnipaper-server")) {
    val projName = name.lowercase(Locale.ENGLISH)
    include(projName)
}

// Include generated paper-api so omnipaper-api can depend on it
include("paper-api")
project(":paper-api").projectDir = file("paper-api")
