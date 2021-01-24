package org.encouragement.app.shared

object SharedMain {
    suspend fun latestEnc() = EncouragementRepository.getLatestEnc()
}