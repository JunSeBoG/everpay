package com.junsebog.everpay.presentation.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.junsebog.everpay.common.Constants
import com.junsebog.everpay.databinding.FragmentHomeBinding
import com.junsebog.everpay.domain.model.Product
import com.junsebog.everpay.presentation.adapter.ProductsAdapter
import com.junsebog.everpay.presentation.ui.activity.payment.PaymentActivity

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("JSBG ME VOLVI A CREAR")
        initUI()
    }

    private fun initUI(){
        binding.homeCurrentDate.text = viewModel.getDate()
        initRecyclerView()
    }

    private fun initRecyclerView(){
        val adapter = ProductsAdapter{ onItemSelected(it) }
        binding.productList.adapter = adapter

        adapter.setItems(viewModel.getProducts())
    }

    private fun onItemSelected(product: Product){
        startActivity(
            Intent(activity, PaymentActivity::class.java).putExtra(Constants.SELECTED_PRODUCT, product)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}