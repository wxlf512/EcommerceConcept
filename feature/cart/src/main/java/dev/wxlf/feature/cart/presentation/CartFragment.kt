package dev.wxlf.feature.cart.presentation

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import dev.wxlf.feature.cart.R
import dev.wxlf.feature.cart.presentation.adapters.CartListAdapter
import dev.wxlf.feature.cart.presentation.models.CartEvent
import dev.wxlf.feature.cart.presentation.models.CartViewState
import dev.wxlf.feature.cart.presentation.viewmodel.CartViewModel
import dev.wxlf.feature.cart.presentation.viewmodel.CartViewModelFactory
import javax.inject.Inject

class CartFragment : Fragment(R.layout.fragment_cart) {

    @Inject
    lateinit var viewModelFactory: CartViewModelFactory
    lateinit var viewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().window.navigationBarColor = requireContext().getColor(R.color.purple)
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cartListAdapter = CartListAdapter(listOf())
        val productsList = view.findViewById<RecyclerView>(R.id.productsList)
        productsList.layoutManager = LinearLayoutManager(requireContext())
        productsList.adapter = cartListAdapter

        viewModel = ViewModelProvider(this, viewModelFactory)[CartViewModel::class.java]
        viewModel.obtainEvent(CartEvent.ScreenShown)
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                CartViewState.LoadingState -> {}
                is CartViewState.LoadedState -> {
                    view.findViewById<TextView>(R.id.totalPrice).text = viewState.data.total
                    view.findViewById<TextView>(R.id.deliveryPrice).text = viewState.data.delivery
                    cartListAdapter.updateData(viewState.data.basket)
                }
                is CartViewState.ErrorState -> {}
            }
        }

        view.findViewById<ImageButton>(R.id.closeButton).setOnClickListener {
            findNavController().popBackStack()
        }
    }
}