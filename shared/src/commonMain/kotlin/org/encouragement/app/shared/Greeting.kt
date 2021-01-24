package org.encouragement.app.shared

import io.ktor.client.*
import io.ktor.client.request.*


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }

    suspend fun networkGreeting() {
        HttpClient().get<String>("")
    }
}
