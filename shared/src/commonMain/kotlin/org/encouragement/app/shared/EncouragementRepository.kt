package org.encouragement.app.shared

import io.ktor.client.*
import io.ktor.client.request.*

internal object EncouragementRepository {
    private const val BASE_URL =
        "https://raw.githubusercontent.com/encouragements/encouragement-app/main/encouragements"
    private const val LATEST_POINTER_URL = "$BASE_URL/latest"

    suspend fun getLatestEnc(): String {
        val client = HttpClient()
        val latestPointer = client.get<String>(LATEST_POINTER_URL)
        val latestUrl = "$BASE_URL/$latestPointer"
        return client.get(latestUrl)
    }
}