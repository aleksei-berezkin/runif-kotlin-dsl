import jetbrains.buildServer.configs.kotlin.*
import runif.*

version = "2026.1"

project {
    pipeline(Status01)
    pipeline(Branch02)
    pipeline(ServiceMsg03)
    pipeline(Test04)
    pipeline(JobFailed05)
}
