package com.junsebog.everpay.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import com.junsebog.everpay.R
import com.junsebog.everpay.common.Common
import com.junsebog.everpay.common.Constants
import com.junsebog.everpay.common.Extensions.toDate
import com.junsebog.everpay.common.Extensions.toHour
import com.junsebog.everpay.databinding.ActivityTransactionDetailBinding
import com.junsebog.everpay.domain.model.Transaction
import com.junsebog.everpay.presentation.ui.fragment.transaction.TransactionFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTransactionDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val internalReference = intent.extras?.get(Constants.INTERNAL_REFERENCE) as Int

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,
            TransactionFragment().apply {
                arguments = bundleOf(Constants.INTERNAL_REFERENCE to internalReference)
            }
        ).commit()

    }

}