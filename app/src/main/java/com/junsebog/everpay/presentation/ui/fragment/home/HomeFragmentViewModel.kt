package com.junsebog.everpay.presentation.ui.fragment.home

import androidx.lifecycle.ViewModel
import com.junsebog.everpay.common.Common
import com.junsebog.everpay.domain.model.Product
import java.time.LocalDateTime

class HomeFragmentViewModel: ViewModel() {

    fun getProducts(): List<Product>{
        return Common.productList.shuffled()
    }

    fun getDate(): String{
        val fecha = LocalDateTime.now()
        return "Hoy es ${fecha.dayOfMonth} de ${fecha.month}, ${fecha.year}"
    }
}