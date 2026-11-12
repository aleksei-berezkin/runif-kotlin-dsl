package runif

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.pipelines.*

object JobFailed05 : Pipeline({
    id("JobFailed05")
    name = "05-job-failed"

    repositories {
        repository(DslContext.settingsRoot)
    }

    job {
        id("Job1")
        name = "Job 1"
        allowReuse = false

        steps {
            script {
                scriptContent = "echo 'Hi'"
                executionMode = BuildStep.ExecutionMode.RUN_ONLY_ON_FAILURE // job-failed
            }
        }
    }
})
