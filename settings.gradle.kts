/*plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}*/

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}

rootProject.name = "dreambook_backend"

include("AuthService")
include("FeedbackService")
include("BillingService")
include("DreamInputService")
include("InterpretationWorker")
include("common:common-db")
include("common:orchestration-core")
include("common:safety-moderation")
include("common:utils")