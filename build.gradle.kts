plugins {
    kotlin("js") version "1.4.20"
    id("org.jetbrains.dokka") version "1.4.10.2"
    id("org.ajoberstar.git-publish") version "3.0.0"
}

group = "de.nycode"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}

kotlin {
    js(LEGACY) {
        browser {
            binaries.executable()
            webpackTask {
                cssSupport.enabled = true
            }
            runTask {
                cssSupport.enabled = true
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
}

gitPublish {
    repoUri.set("https://github.com/NyCodeGHG/conways-game-of-life.git")
    branch.set("gh-pages")
    contents {
        from("build/distributions")
        exclude("*.map")
        from("build/dokka")
    }
}

tasks.dokkaHtml.configure {
    outputDirectory.set(buildDir.resolve("dokka"))
}
