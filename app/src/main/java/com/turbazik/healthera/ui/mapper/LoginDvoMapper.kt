package com.turbazik.healthera.ui.mapper

import com.turbazik.healthera.domain.model.LoginEntity
import com.turbazik.healthera.ui.model.LoginDvo
import com.turbazik.healthera.utils.Mapper

class LoginDvoMapper : Mapper<LoginEntity, LoginDvo>() {

    override fun map(from: LoginEntity): LoginDvo {
        return LoginDvo(
            userId = from.userId,
            token = from.token
        )
    }
}
