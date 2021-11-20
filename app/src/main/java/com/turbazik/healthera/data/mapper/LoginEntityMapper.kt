package com.turbazik.healthera.data.mapper

import com.turbazik.healthera.data.model.LoginDto
import com.turbazik.healthera.domain.model.LoginEntity
import com.turbazik.healthera.utils.Mapper

class LoginEntityMapper : Mapper<LoginDto, LoginEntity>() {

    override fun map(from: LoginDto): LoginEntity {
        return LoginEntity(
            userId = from.aux?.tokenPayload?.userId,
            token = from.data?.first()?.token
        )
    }
}
