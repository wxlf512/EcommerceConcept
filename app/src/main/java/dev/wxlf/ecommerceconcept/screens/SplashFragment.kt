package dev.wxlf.ecommerceconcept.screens

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import dev.wxlf.ecommerceconcept.R

class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.statusBarColor = requireActivity().getColor(R.color.purple)
        requireActivity().window.navigationBarColor = requireContext().getColor(R.color.purple)
        super.onViewCreated(view, savedInstanceState)
        view.postDelayed({
            findNavController().navigate(Uri.parse("ecommerceapp://explorer"),
            navOptions {
                popUpTo(R.id.nav_graph_app) {
                    inclusive = true
                }
            })
        }, 1000)
    }
}

