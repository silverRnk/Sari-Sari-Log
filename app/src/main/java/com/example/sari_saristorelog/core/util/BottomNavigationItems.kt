package com.example.sari_saristorelog.core.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.sari_saristorelog.R

sealed class BottomNavigationItems(val route: String, @StringRes val label: Int, @DrawableRes val icon: Int){
    object Home: BottomNavigationItems(route = Route.HOME_SCREEN, label = R.string.nav_item_1, icon = R.drawable.ic_home_24)
    object Add: BottomNavigationItems(route = Route.ADD_EDIT_TRANSACTION_SCREEN, label = R.string.nav_item_2, icon = R.drawable.ic_add_circle_outline_24)

    companion object NavList{
        val navItems = listOf<BottomNavigationItems>(
            Home,
            Add
        )
    }

}


