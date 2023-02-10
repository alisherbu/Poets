package kaa.alisherbu.baxtsizlar.main

sealed interface Event {
    class MenuItemClicked(val id: Int) : Event
}
