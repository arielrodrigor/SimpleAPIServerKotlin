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
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.LoggerFactory
import org.slf4j.event.Level

@Serializable
data class Movie(val title: String)

fun main() {
    val log = LoggerFactory.getLogger("ApplicationKt")

    embeddedServer(Netty, port = 8080) {
        install(DefaultHeaders)
        install(CallLogging) {
            level = org.slf4j.event.Level.DEBUG
        }
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
                try {
                    call.respond(ThymeleafContent("index", emptyMap<String, Any>()))
                } catch (e: Exception) {
                    log.error("Error serving / route", e)
                    call.respond(HttpStatusCode.InternalServerError, "Internal Server Error")
                }
            }
            get("/movies") {
                try {
                    val movies = listOf(
                        Movie("The Shawshank Redemption"),
                        Movie("The Godfather"),
                        Movie("The Dark Knight"),
                        Movie("Pulp Fiction"),
                        Movie("Forrest Gump"),
                        Movie("Inception")
                    )
                    call.respond(movies)
                } catch (e: Exception) {
                    log.error("Error serving /movies route", e)
                    call.respond(HttpStatusCode.InternalServerError, "Internal Server Error" +e)
                }
            }
        }
    }.start(wait = true)
}