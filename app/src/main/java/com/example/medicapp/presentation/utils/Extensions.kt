package com.example.medicapp.presentation.utils

import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.appbar.AppBarLayout

fun Fragment.refreshLayoutOffsetTopPanel(refreshLayout: SwipeRefreshLayout, appBarLayout: AppBarLayout){
    var targenOffest = true
    refreshLayout.isEnabled = true

    val listener = AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
        targenOffest = verticalOffset == 0
        refreshLayout.isEnabled = targenOffest
    }
    appBarLayout.addOnOffsetChangedListener(listener)
}