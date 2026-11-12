package runif

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.pipelines.*

object Branch02 : Pipeline({
    id("Branch02")
    name = "02-Branch"

    repositories {
        repository(DslContext.settingsRoot)
    }

    job {
        id("Job1")
        name = "Job 1"
        allowReuse = false

        steps {
            script {
                scriptContent = "echo 'Build'"
            }

            script {
                scriptContent = "echo 'Deploy'"
                conditions {
                    equals("teamcity.build.branch", "main")
                }
            }
        }
    }
})
