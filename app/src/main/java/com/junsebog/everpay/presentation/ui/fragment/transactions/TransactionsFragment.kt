package com.junsebog.everpay.presentation.ui.fragment.transactions

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.junsebog.everpay.common.Constants
import com.junsebog.everpay.databinding.FragmentTransactionsBinding
import com.junsebog.everpay.domain.model.Transaction
import com.junsebog.everpay.presentation.adapter.TransactionsAdapter
import com.junsebog.everpay.presentation.ui.activity.TransactionDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionsFragment : Fragment() {

    private var _binding : FragmentTransactionsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TransactionsFragmentViewModel by viewModels()

    private lateinit var adapter: TransactionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TransactionsAdapter{
            onTransactionSelected(it)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.transactionList.collect{
                setupRecyclerView(it)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        println("TRANSACCION STARTED")
        viewModel.getTransactionList()
    }

    private fun setupRecyclerView(list: List<Transaction>){
        binding.transctionList.adapter = adapter
        adapter.setItems(list)
    }

    private fun onTransactionSelected(reference: Int){
        startActivity(
            Intent(activity, TransactionDetailActivity::class.java).putExtra(Constants.INTERNAL_REFERENCE, reference)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}