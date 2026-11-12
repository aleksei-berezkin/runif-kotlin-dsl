package runif

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.pipelines.*

object ServiceMsg03 : Pipeline({
    id("ServiceMsg03")
    name = "03-service-msg"

    repositories {
        repository(DslContext.settingsRoot)
    }

    params {
        param("change_me", "false")
    }

    job {
        id("Job1")

        steps {
            script {
                scriptContent = "echo \"Step 1\""
                conditions {
                    equals("change_me", "true")
                }
            }
            script {
                scriptContent = """
                    echo "Step 2"
                    echo "##teamcity[setParameter name='change_me' value='true']"
                """.trimIndent()
            }
            script {
                scriptContent = "echo \"Step 3\""
                conditions {
                    equals("change_me", "true")
                }
            }
        }
    }
})
