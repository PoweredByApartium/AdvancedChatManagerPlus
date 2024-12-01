allprojects {
    dependencies {
        api("com.querydsl:querydsl-sql:5.1.0")
        api(project(":api"))
        api(project(":impl"))
        compileOnlyApi("net.kyori:adventure-api:4.17.0")
        compileOnlyApi("net.kyori:adventure-text-minimessage")
        compileOnlyApi("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT")

        // todo split
        api("com.fasterxml.jackson.core:jackson-core:2.15.2")
        api("com.fasterxml.jackson.module:jackson-module-parameter-names:2.15.2")
        api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")

    }
}