package com.pluu.sample.codeforreadability.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SampleRepository {
    fun sendLogFlow(): Flow<LogResult>
}

class SampleRepositoryImpl(
    private val sampleApi: SampleApi
) : SampleRepository, SampleApi by sampleApi {
    override fun sendLogFlow() = flow {
        emit(sampleApi.sendLog())
    }
}
