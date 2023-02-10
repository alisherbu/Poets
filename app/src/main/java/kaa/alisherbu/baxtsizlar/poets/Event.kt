package kaa.alisherbu.baxtsizlar.poets

sealed interface Event {
    class ItemClicked(val id: Int) : Event
}
