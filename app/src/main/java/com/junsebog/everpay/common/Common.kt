package com.junsebog.everpay.common

import com.junsebog.everpay.R
import com.junsebog.everpay.domain.model.Product
import java.math.BigInteger
import java.security.MessageDigest
import java.security.SecureRandom
import java.text.SimpleDateFormat
import java.util.*

object Common {

    fun getRandom(): String {
        return BigInteger(130, SecureRandom()).toString()
    }

    fun getCurrentDateFormat(): String {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault()).format(Date())
    }

    fun getBase64(input: ByteArray): String {
        val encodedBytes: ByteArray = Base64.getEncoder().encode(input)

        return String(encodedBytes)
    }

    fun getSHA256(input: String): ByteArray {
        val mDigest: MessageDigest = MessageDigest.getInstance("SHA-256")
        return mDigest.digest(input.toByteArray())
    }

    val productList = listOf(
        Product(reference = "Bananos", description = "Gajo de bananos premium, importados de Costa Rica, totalmente frescos.", total = 700, urlImage = R.drawable.bananas),
        Product(reference = "Papilla", description = "Deliciosa papilla de frutas naturales, perfecta para bebés.", total = 4200, urlImage = R.drawable.papilla),
        Product(reference = "Pan francés", description = "Pan Francés importado completamente artesanal.", total = 31000, urlImage = R.drawable.bread),
        Product(reference = "Cubeta de Huevos", description = "Conjunto de huevos seleccionados de las mejores gallinas de la granja.", total = 52000, urlImage = R.drawable.eggs),
        Product(reference = "Manzanas", description = "3 Manzanas importadas directamente de los bosques europeos.", total = 70000, urlImage = R.drawable.apples),
        Product(reference = "Queso azul", description = "Queso azul de reserva italiana, completamente artesanal y único en su clase.", total = 1700000, urlImage = R.drawable.cheese),
        Product(reference = "Jugo de manzana en caja", description = "Caja de un litro repleta de un refrescante jugo de manzana.", total = 6000, urlImage = R.drawable.applejuice)
    )

    val statusCodes = hashMapOf(
        "APPROVED" to "APROBADO",
        "REJECTED" to "RECHAZADO"
    )

    val statusColors = hashMapOf(
        "APPROVED" to R.color.green,
        "REJECTED" to R.color.red
    )

    val statusIcons = hashMapOf(
        "APPROVED" to R.drawable.ic_success,
        "REJECTED" to R.drawable.ic_error
    )
}