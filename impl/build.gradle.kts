allprojects {
    dependencies {
        api("com.querydsl:querydsl-sql:5.1.0")

        api(project(":api"))
        compileOnlyApi("net.kyori:adventure-api:4.17.0")
        compileOnlyApi("net.kyori:adventure-text-minimessage")
        compileOnlyApi("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT")
        implementation("org.apache.commons:commons-text:1.12.0")
        implementation("net.objecthunter:exp4j:0.4.8")
        implementation("org.apache.commons:commons-lang3:3.17.0")

        // todo split
        api("com.fasterxml.jackson.core:jackson-core:2.15.2")
        api("com.fasterxml.jackson.module:jackson-module-parameter-names:2.15.2")
        api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")


        testImplementation(platform("org.junit:junit-bom:5.9.0"))
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testImplementation("org.junit.jupiter:junit-jupiter-params")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
        testRuntimeOnly("com.google.guava:guava:33.3.1-jre")
        testRuntimeOnly("net.kyori:adventure-api:4.17.0")
        testRuntimeOnly("net.kyori:adventure-text-minimessage")
    }
}