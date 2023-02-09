package kaa.alisherbu.baxtsizlar

import kotlinx.coroutines.CoroutineDispatcher

interface PoetDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}
