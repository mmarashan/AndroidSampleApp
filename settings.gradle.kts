rootProject.name = "Ktor example"

/* App */
include(
    ":app"
)

/* Core */
include(
    ":core:network",
    ":core:common",
    ":core:feature-api",
    ":core:messari-api",
    ":core:ui-kit",
)

/* Feature */
include(
    ":feature:crypto-assets-list"
)
