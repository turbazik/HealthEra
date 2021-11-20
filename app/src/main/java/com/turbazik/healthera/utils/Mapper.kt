package com.turbazik.healthera.utils


abstract class Mapper<FROM, TO> {

    abstract fun map(from: FROM): TO
}