plugins {
    kotlin("js") version "1.4.20"
    id("org.ajoberstar.git-publish") version "3.0.0"
}

group = "de.nycode"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-js"))
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
    repoUri.set("git@github.com:NyCodeGHG/conways-game-of-life.git")
    branch.set("gh-pages")
    contents {
        from("build/distributions")
        exclude("*.map")
    }
}

task("publish") {
    dependsOn(tasks.clean, tasks.build, tasks.gitPublishPush)
}
