package runif

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.pipelines.*

object StatusAndBranch01a : Pipeline({
    id("StatusAndBranch01a")
    name = "01a-StatusAndBranch"

    repositories {
        repository(DslContext.settingsRoot)
    }

    job {
        id("Job1")
        name = "Job1"
        allowReuse = false

        steps {
            script {
                scriptContent = "echo 'Deploy'"
            }

            script {
                scriptContent = "echo 'Roll back'"
                executionMode = BuildStep.ExecutionMode.ALWAYS // any
                conditions {
                    equals("teamcity.build.branch", "main")
                }
            }
        }
    }
})
