dependencies {
    api("com.querydsl:querydsl-sql:5.1.0")
    api(project(":api"))
    compileOnlyApi("net.kyori:adventure-api:4.14.0")
    compileOnlyApi("net.kyori:adventure-text-minimessage")

    // todo split
    api("com.fasterxml.jackson.core:jackson-core:2.15.2")
    api("com.fasterxml.jackson.module:jackson-module-parameter-names:2.15.2")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")


}