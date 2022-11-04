package dev.wxlf.feature.explorer.presentation

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.wxlf.feature.explorer.R

class ExplorerFragment : Fragment(R.layout.fragment_explorer) {

    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().window.statusBarColor = Color.WHITE
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.detailsButton).setOnClickListener {
            findNavController().navigate(Uri.parse("ecommerceapp://details"))
        }
    }
}