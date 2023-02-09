package kaa.alisherbu.baxtsizlar.poets

import kotlinx.coroutines.CoroutineDispatcher

interface PoetDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}
