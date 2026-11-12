package runif

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.pipelines.*

object Test04 : Pipeline({
    id("Test04")
    name = "04-test"

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
                    echo "Running tests -- Step 1"
                    echo "##teamcity[testStarted name='testHi']"
                    echo "##teamcity[testFailed name='testHi' message='boom']"
                    echo "##teamcity[testFinished name='testHi']"
                """.trimIndent()
            }

            script {
                scriptContent = "echo 'Cleanup -- Step 2'"
                executionMode = BuildStep.ExecutionMode.RUN_ONLY_ON_FAILURE // job-failed
            }

            script {
                scriptContent = "echo 'Step 3'"
                executionMode = BuildStep.ExecutionMode.DEFAULT // steps-successful, produces no run-if
            }
        }
    }
})
