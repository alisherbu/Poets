package kaa.alisherbu.baxtsizlar

sealed interface Event {
    class ItemClicked(val id: Int) : Event
}
