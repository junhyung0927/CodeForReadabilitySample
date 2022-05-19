package com.pluu.sample.codeforreadability.data

import kotlinx.coroutines.flow.Flow

class FakeSampleRepository: SampleRepository {
    override suspend fun sendLog(): LogResult {
        TODO("Not yet implemented")
    }

    override fun sendLogFlow(): Flow<LogResult> {
        TODO("Not yet implemented")
    }
}