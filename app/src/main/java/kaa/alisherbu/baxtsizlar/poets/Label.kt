package kaa.alisherbu.baxtsizlar.poets

sealed interface Label {
    class Navigated(val id: Int) : Label
}
