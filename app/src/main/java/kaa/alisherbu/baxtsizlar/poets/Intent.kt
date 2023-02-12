package kaa.alisherbu.baxtsizlar.poets

sealed interface Intent {
    class ItemClicked(val id: Int) : Intent
}
