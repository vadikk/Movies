package com.example.common.nav

sealed class NavScreen() {
    object Popular: NavScreen()
    data class MovieDetail(val route: String): NavScreen()
}

class NavManager {
    private var navEventListener: ((navScreen: NavScreen) -> Unit)? = null

    fun navigate(navScreen: NavScreen) {
        navEventListener?.invoke(navScreen)
    }

    fun setOnNavEvent(navEventListener: (navScreen: NavScreen) -> Unit) {
        this.navEventListener = navEventListener
    }
}