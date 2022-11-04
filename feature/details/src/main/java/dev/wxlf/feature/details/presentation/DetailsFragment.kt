package dev.wxlf.feature.details.presentation

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import dev.wxlf.feature.details.R

class DetailsFragment : Fragment(R.layout.fragment_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.textView).setOnClickListener {
            findNavController().navigate(Uri.parse("ecommerceapp://explorer"),
            navOptions {
                popUpTo(R.id.nav_graph_details) {
                    inclusive = true
                }
            })
        }
    }
}