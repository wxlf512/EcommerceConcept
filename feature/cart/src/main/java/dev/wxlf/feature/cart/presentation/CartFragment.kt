package dev.wxlf.feature.cart.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.wxlf.feature.cart.R

class CartFragment : Fragment(R.layout.fragment_cart) {

    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().window.navigationBarColor = requireContext().getColor(R.color.purple)
        super.onCreate(savedInstanceState)
    }
}