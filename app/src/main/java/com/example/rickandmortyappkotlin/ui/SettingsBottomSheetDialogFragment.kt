package com.example.rickandmortyappkotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmortyappkotlin.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SettingsBottomSheetDialogFragment: BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_seetings_bottom_sheet_dialog, container, false)

        return view
    }
}