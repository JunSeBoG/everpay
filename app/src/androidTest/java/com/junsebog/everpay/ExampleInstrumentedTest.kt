package com.junsebog.everpay

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.junsebog.everpay.common.Constants
import com.junsebog.everpay.data.local.AppDatabase
import com.junsebog.everpay.data.local.LocalDataSource
import com.junsebog.everpay.data.remote.EvertecApi
import com.junsebog.everpay.data.remote.RemoteDataSource
import com.junsebog.everpay.data.repository.TransactionRepositoryImpl
import com.junsebog.everpay.domain.model.Card
import com.junsebog.everpay.domain.model.Payer
import com.junsebog.everpay.domain.model.Payment
import com.junsebog.everpay.domain.model.Product
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest

import org.junit.Test
import org.junit.runner.RunWith

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    val context =  InstrumentationRegistry.getInstrumentation().context

    val api = Retrofit.Builder()
        .baseUrl(Constants.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EvertecApi::class.java)

    val db = Room.inMemoryDatabaseBuilder(
        context,
        AppDatabase::class.java
    ).build()

    val dao = db.transactionDao()

    private val localDataSource =  LocalDataSource(dao)
    private val remoteDataSource =  RemoteDataSource(api)

    val a = TransactionRepositoryImpl(
    localDataSource = localDataSource,
    remoteDataSource = remoteDataSource)


    val payer = Payer(
        name = "Sebastian",
        surname = "Boada",
        email = "sebas@gmail.com",
        documentType = "CC",
        document = "10101294356",
        mobile = "3162727124"
    )
    val paymentInfo = Product(
        total = 360000,
        currency = "COP",
        reference = "JSBG_2022",
        description = "Compra en pagina web"
    )
    val card = Card(
        number = "4005580000000040",
        cvv = "123",
        expiration = "12/22"
    )

    val payment = Payment(
        payer = payer,
        product = paymentInfo,
        card = card,
        installments = 1
    )

    @Test
    fun useAppContext() = runTest {
       a.makePayment(payment)

        //println(api.makeTransaction(PaymentDto( payment))) 361869
       assert(a.getTransactionList().first().isNotEmpty())
    }
}