package com.junsebog.everpay.data.remote.dto

import com.junsebog.everpay.common.Common
import com.junsebog.everpay.common.Constants

class Auth {
    var login: String = Constants.API_LOGIN
    var nonce: String
    var seed: String
    var tranKey: String
    private var myNonce: String = Common.getRandom()

    init {
        nonce = Common.getBase64(myNonce.toByteArray())
        seed = Common.getCurrentDateFormat()
        tranKey = Common.getBase64(Common.getSHA256(myNonce + seed + Constants.API_TRANKEY))
    }
}






