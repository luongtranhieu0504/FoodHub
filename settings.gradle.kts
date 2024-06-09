
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()
            maven { url = uri("https://jitpack.io") }
            maven { url = uri("https://maven.google.com") }
            maven {
                url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
                credentials {
                    username = "mapbox"
                    password = ("MAPBOX_DOWNLOADS_TOKEN") as String
                }
            }
            maven { url =  uri("https://mapbox.bintray.com/mapbox") }

    }
}


rootProject.name = "FoodHub"
include(":app")
