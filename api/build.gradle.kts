plugins {
    id("java")
}

group = project.parent!!.group
version = project.parent!!.version

dependencies {

    compileOnly("net.kyori:adventure-api:4.17.0")

}