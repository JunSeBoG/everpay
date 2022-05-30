package com.junsebog.everpay.presentation.ui.fragment.cardstep

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.junsebog.everpay.R
import com.junsebog.everpay.common.Constants
import com.junsebog.everpay.databinding.FragmentFinalStepBinding
import com.junsebog.everpay.presentation.ui.activity.TransactionDetailActivity
import com.junsebog.everpay.presentation.ui.activity.payment.PaymentActivityFragmentViewModel

class CardStepFragment : Fragment() {

    private var _binding : FragmentFinalStepBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PaymentActivityFragmentViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFinalStepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextButton.setOnClickListener {

            viewModel.setCard(
                number = binding.cardNumber.text.toString(),
                cvv = binding.cvv.toString(),
                expiration = binding.expDate.toString()
            )

            viewModel.setInstallments(binding.installments.text.toString().toInt())

            viewModel.makeTransaction()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.internalReference.collect{
                if (it != 0){
                    launchActivity(it)
                }
            }
        }

        binding.prevButton.setOnClickListener {
            activity?.findViewById<ViewPager2>(R.id.paymentPager)?.currentItem = 0
        }
    }

    private fun launchActivity(internalReference: Int){
        startActivity(
            Intent(activity, TransactionDetailActivity::class.java).putExtra(Constants.INTERNAL_REFERENCE, internalReference)
        )

        activity?.finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}