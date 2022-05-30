package com.junsebog.everpay.presentation.ui.fragment.transaction

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.junsebog.everpay.R
import com.junsebog.everpay.common.Common
import com.junsebog.everpay.common.Constants
import com.junsebog.everpay.common.Extensions.toDate
import com.junsebog.everpay.common.Extensions.toHour
import com.junsebog.everpay.databinding.FragmentCheckoutStepBinding
import com.junsebog.everpay.domain.model.Transaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionFragment : Fragment() {

    private var _binding : FragmentCheckoutStepBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TransactionFragmentViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val internalReference = it.getInt(Constants.INTERNAL_REFERENCE)
            viewModel.getTransaction(internalReference)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCheckoutStepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.transaction.collect{
                initBinding(it)
            }
        }

        binding.nextButton.setOnClickListener {
            finishActivity()
        }

        binding.deleteTransaction.setOnClickListener {
            viewModel.deleteTransaction()

            Handler(Looper.getMainLooper()).postDelayed({
               finishActivity()
            }, 500)
        }
    }

    private fun initBinding(transaction: Transaction){
        binding.transactionIcon.setImageResource(Common.statusIcons[transaction.status]!!)
        binding.constraintLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), Common.statusColors[transaction.status]!!))
        binding.transactionStatus.text = Common.statusCodes[transaction.status]
        binding.transactionReference.text = getString(R.string.internal_reference, transaction.internalReference.toString())
        binding.transactionRef.text = transaction.reference
        binding.transactionAmount.text = transaction.amount.toString()
        binding.transactionPayment.text = getString(R.string.card_item_message, transaction.franchiseName, transaction.lastDigits)
        binding.transactionDate.text = transaction.transactionDate.toDate()
        binding.transactionHour.text = transaction.transactionDate.toHour()
    }

    private fun finishActivity(){
        activity?.finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}