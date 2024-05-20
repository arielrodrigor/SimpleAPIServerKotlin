package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import io.ktor.http.*



fun main() {
    embeddedServer(Netty, port = 8080) {
        install(DefaultHeaders)
        install(CallLogging)
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        install(Thymeleaf) {
            setTemplateResolver(ClassLoaderTemplateResolver().apply {
                suffix = ".html"
                characterEncoding = "utf-8"
            })
        }
        routing {
            get("/") {
                call.respond(ThymeleafContent("index", null))
            }
            get("/movies") {
                val movies = listOf(
                    Movie("The Shawshank Redemption"),
                    Movie("The Godfather"),
                    Movie("The Dark Knight"),
                    Movie("Pulp Fiction"),
                    Movie("Forrest Gump"),
                    Movie("Inception")
                )
                call.respond(movies)
            }
        }
    }.start(wait = true)
}