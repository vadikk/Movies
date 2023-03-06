package com.example.common

import com.example.common.nav.NavManager
import org.koin.dsl.module

val navigationModule = module {
    single { NavManager() }
}