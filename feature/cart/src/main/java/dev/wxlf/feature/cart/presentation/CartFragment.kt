package dev.wxlf.feature.cart.presentation

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.wxlf.feature.cart.R

class CartFragment : Fragment(R.layout.fragment_cart) {

    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().window.navigationBarColor = requireContext().getColor(R.color.purple)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.closeButton).setOnClickListener {
            findNavController().popBackStack()
        }
    }
}