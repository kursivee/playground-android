package com.kursivee.buildconfig.ui.main

import androidx.lifecycle.ViewModel
import com.kursivee.buildconfig.BuildConfig

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    /**
     * Uncalled function that should not be in security build
     */
    fun testNotInSecurityBuild() {

    }

    /**
     * Called function that checks what it removes from an if/else perspective
     *
     * Kotlin Bytecode generation will automatically remove unreachable conditions
     * Using `BuildConfig` R8 will remove if(BuildConfig.condition == false)
     */
    fun testIfElseSecurityBuild() {
        if(BuildConfig.DEBUG) {
            // Removed from security build
            println("BuildConfig.DEBUG")
        }

        if(!BuildConfig.DEBUG) {
            // Kept in security build
            println("!BuildConfig.DEBUG")
        } else {
            // Removed from security build
            println("else for !BuiildConfig.DEBUG")
        }

        if(true) {
            // Kept in security build
            println("if is always true")
        } else {
            // Removed from security build
            println("else")
        }
    }
}