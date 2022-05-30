package com.junsebog.everpay.presentation.ui.activity.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import com.junsebog.everpay.common.Constants
import com.junsebog.everpay.databinding.ActivityPaymentBinding
import com.junsebog.everpay.domain.model.Product
import com.junsebog.everpay.presentation.adapter.ViewPagerAdapter
import com.junsebog.everpay.presentation.ui.fragment.cardstep.CardStepFragment
import com.junsebog.everpay.presentation.ui.fragment.payerstep.PayerStepFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPaymentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        product = intent.extras?.get(Constants.SELECTED_PRODUCT) as Product

        setUpViewPager()
    }

    private fun setUpViewPager(){
        val fragmentList = listOf(
            PayerStepFragment().apply {
                arguments = bundleOf(Constants.SELECTED_PRODUCT to product)
            },
            CardStepFragment()
        )

        val adapter = ViewPagerAdapter(fragmentList, this)
        val viewpager = binding.paymentPager
        viewpager.adapter = adapter
        viewpager.isUserInputEnabled = false
    }
}