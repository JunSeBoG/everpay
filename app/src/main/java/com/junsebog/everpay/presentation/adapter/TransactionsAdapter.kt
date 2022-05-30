package com.junsebog.everpay.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.junsebog.everpay.R
import com.junsebog.everpay.common.Common.statusCodes
import com.junsebog.everpay.common.Common.statusColors
import com.junsebog.everpay.common.Common.statusIcons
import com.junsebog.everpay.common.Extensions.toCurrency
import com.junsebog.everpay.common.Extensions.toDate
import com.junsebog.everpay.databinding.TransactionItemBinding
import com.junsebog.everpay.domain.model.Transaction

class TransactionsAdapter(
    private val onClick: (Int) -> Unit
): RecyclerView.Adapter<TransactionsViewHolder>() {

    private var transactionList: List<Transaction> = emptyList()

    fun setItems(list: List<Transaction>){
        transactionList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item , parent, false)
        return TransactionsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionsViewHolder, position: Int) {
        holder.bind(transactionList[position], onClick)
    }

    override fun getItemCount(): Int = transactionList.size
}

class TransactionsViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val binding = TransactionItemBinding.bind(view)

    fun bind(transaction: Transaction, onClick: (Int) -> Unit){
        binding.imageView2.setImageResource(statusIcons[transaction.status]!!)
        binding.itemReference.text = transaction.reference
        binding.itemDate.text = transaction.transactionDate.toDate()
        binding.itemAmount.text = itemView.context.getString(R.string.amount_values, transaction.amount.toCurrency())
        binding.itemStatus.apply {
            text = statusCodes[transaction.status]
            setTextColor(ContextCompat.getColor(itemView.context, statusColors[transaction.status]!!))
        }

        binding.itemCard.text = itemView.context.getString(R.string.card_item_message, transaction.franchiseName, transaction.lastDigits)

        itemView.setOnClickListener {
            onClick(transaction.internalReference)
        }
    }
}
