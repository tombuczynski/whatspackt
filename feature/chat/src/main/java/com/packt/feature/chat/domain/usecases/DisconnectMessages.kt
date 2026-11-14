package com.packt.feature.chat.domain.usecases

import com.packt.feature.chat.domain.IMessagesRepository
import com.packt.feature.chat.domain.model.Message
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Tom Buczynski on 10.11.2026.
 */
class DisconnectMessages @Inject constructor (
    private val repo: IMessagesRepository
) {
    suspend operator fun invoke() {
        repo.disconnect()
    }
}