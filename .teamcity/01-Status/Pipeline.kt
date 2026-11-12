package runif

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.pipelines.*

object Status01 : Pipeline({
    id("Status01")
    name = "01-Status"

    repositories {
        repository(DslContext.settingsRoot)
    }

    job {
        id("Job1")
        name = "Job 1"
        allowReuse = false

        steps {
            script {
                scriptContent = """
                    echo "Build"
                    exit 1
                """.trimIndent()
            }

            script {
                scriptContent = "echo 'Deploy'"
            }

            script {
                scriptContent = "echo 'Cleanup'"
                executionMode = BuildStep.ExecutionMode.RUN_ONLY_ON_FAILURE // job-failed
            }
        }
    }
})
