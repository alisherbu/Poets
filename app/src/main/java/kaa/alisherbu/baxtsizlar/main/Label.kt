package kaa.alisherbu.baxtsizlar.main

sealed interface Label {
    class MenuItemClicked(val id: Int) : Label
    object NavigationIconClicked : Label
}
