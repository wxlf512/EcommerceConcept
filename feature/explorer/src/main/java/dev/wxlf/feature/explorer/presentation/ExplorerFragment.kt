package dev.wxlf.feature.explorer.presentation

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils
import dev.wxlf.feature.explorer.R

@ExperimentalBadgeUtils
class ExplorerFragment : Fragment(R.layout.fragment_explorer) {

    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().window.statusBarColor = Color.WHITE
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavBar = view.findViewById<LinearLayout>(R.id.bottomNavView)
        val cartIcon = bottomNavBar.findViewById<ImageView>(R.id.cartIcon)
        cartIcon.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                BadgeDrawable.create(requireContext()).apply {
                    number = 2
                    this.backgroundColor = requireContext().getColor(R.color.orange)
                    BadgeUtils.attachBadgeDrawable(this, cartIcon)
                }
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

        cartIcon.setOnClickListener {
            findNavController().navigate(Uri.parse("ecommerceapp://cart"))
        }
    }
}