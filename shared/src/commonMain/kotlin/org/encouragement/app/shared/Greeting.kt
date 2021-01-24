package org.encouragement.app.shared

import io.ktor.client.*
import io.ktor.client.request.*


class Greeting {
    suspend fun greeting(): String {
        val firstGreeting = "Hello, ${Platform().platform}!"
        val secondGreeting = networkGreeting()

        return "$firstGreeting\n$secondGreeting"
    }

    suspend fun networkGreeting(): String {
        val encUrl = "https://raw.githubusercontent.com/encouragements/encouragement-app/main/encouragements/1"
        return HttpClient().get(encUrl)
    }
}
