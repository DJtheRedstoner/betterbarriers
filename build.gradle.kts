plugins {
    id("fabric-loom") version "0.12-SNAPSHOT"
    id("io.github.juuxel.loom-quiltflower") version "1.7.3"
    id("maven-publish")
}

version = "1.0.0"
group = "me.djtheredstoner"

dependencies {
    minecraft("com.mojang:minecraft:1.19.3")
    mappings("net.fabricmc:yarn:1.19.3+build.5:v2")
    modImplementation("net.fabricmc:fabric-loader:0.14.12")

    modImplementation("net.fabricmc.fabric-api:fabric-api:0.70.0+1.19.3")
}

tasks.compileJava {
    options.release.set(17)
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand(mapOf("version" to project.version))
    }
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${project.name}"}
    }
}