package com.junsebog.everpay.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.junsebog.everpay.R
import com.junsebog.everpay.common.Extensions.toCurrency
import com.junsebog.everpay.databinding.ProdutcItemBinding
import com.junsebog.everpay.domain.model.Product

class ProductsAdapter(
    val onClick: (product: Product) -> Unit
): RecyclerView.Adapter<ProductsViewHolder>() {

    private var productsList: List<Product> = emptyList()

    fun setItems(list: List<Product>){
        productsList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.produtc_item, parent, false)
        return ProductsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(productsList[position], onClick)
    }

    override fun getItemCount(): Int = productsList.size
}

class ProductsViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val binding = ProdutcItemBinding.bind(view)

    fun bind(product: Product, onClick: (product: Product) -> Unit){

        Glide.with(itemView.context)
            .load(product.urlImage)
            .into(binding.imageView)

        binding.productName.text = product.reference
        binding.productPrice.text = itemView.context.getString(R.string.amount_values, product.total.toCurrency())
        binding.productDescription.text = product.description

        binding.productPay.setOnClickListener {
            onClick(product)
        }

    }
}