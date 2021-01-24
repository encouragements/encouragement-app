package org.encouragement.app.shared

import io.ktor.client.*
import io.ktor.client.request.*

internal object EncouragementRepository {
    private const val BASE_URL =
        "https://raw.githubusercontent.com/encouragements/encouragement-app/main/encouragements"
    private const val LIST_URL = "$BASE_URL/list"

    private val client = HttpClient()

    suspend fun getLatestEnc(): Encouragement {
        val encPointers = client.get<String>(LIST_URL)
        val pointers = encPointers.split(",")
        val latestPointer = pointers.last()
        val latestUrl = "$BASE_URL/$latestPointer"
        val latestBody = client.get<String>(latestUrl)
        val latestNumber = latestPointer.toInt()
        return Encouragement(latestNumber, latestBody)
    }
}