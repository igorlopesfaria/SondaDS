package br.com.sondasd.cell

import android.view.View

interface StateView {
    var currentView: View
    var currentState: State
    fun changeViewState()

    enum class State(var id: Int) {
        NORMAL(0), SKELETON(1);
    }
}
