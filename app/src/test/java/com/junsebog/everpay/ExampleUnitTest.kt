package com.junsebog.everpay

import com.junsebog.everpay.data.remote.dto.Auth
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {



    @Test
    fun addition_isCorrect() {
        val auth = Auth()

        println("nonce ${auth.nonce}")
        println("trankey ${auth.tranKey}")
        println("seed ${auth.seed}")
        assertEquals(4, 2 + 2)
    }
}