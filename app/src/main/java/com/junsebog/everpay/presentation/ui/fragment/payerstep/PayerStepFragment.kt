package com.junsebog.everpay.presentation.ui.fragment.payerstep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.junsebog.everpay.R
import com.junsebog.everpay.common.Constants
import com.junsebog.everpay.databinding.FragmentPayerStepBinding
import com.junsebog.everpay.domain.model.Product
import com.junsebog.everpay.presentation.ui.activity.payment.PaymentActivityFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PayerStepFragment : Fragment() {

    private var _binding : FragmentPayerStepBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PaymentActivityFragmentViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val productSelected = it.getSerializable(Constants.SELECTED_PRODUCT) as Product
            viewModel.setProduct(productSelected)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        _binding = FragmentPayerStepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextButton.setOnClickListener {

            /*viewModel.setPayer(
                name = binding.payerName.text.toString(),
                surname = binding.payerSurname.text.toString(),
                email = binding.payerEmail.text.toString(),
                documentType = binding.payerDocType.text.toString(),
                document = binding.payerDoc.text.toString(),
                mobile = binding.payerPhone.text.toString()
            )*/

            viewModel.setPayer(
                name = "Sebastian",
                surname = "Boada",
                email = "sebas@gmail.com",
                documentType = "CC",
                document = "10101294356",
                mobile = "3162727124"
            )

            /*viewModel.setCard(
                number = "4111111111111111",
                cvv = "123",
                expiration = "12/22"
            )*/

            //viewModel.makeTransaction()

            activity?.findViewById<ViewPager2>(R.id.paymentPager)?.currentItem = 1
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}