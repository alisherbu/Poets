package kaa.alisherbu.baxtsizlar.main

sealed interface Intent {
    class MenuItemClicked(val id: Int) : Intent
    object NavigationIconClicked : Intent
}
