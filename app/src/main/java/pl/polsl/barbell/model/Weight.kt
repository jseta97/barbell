package pl.polsl.barbell.model

import java.util.*

class Weight(
    val userUuid: String? = null,
    var value: Double? = null,
    var date: Date? = null
) {
    data class Builder(
        private var userUuid: String? = null,
        private var value: Double? = null,
        private var date: Date? = null
    ) {
        fun value(value: Double?) = apply { this.value = value }
        fun value(userUuid: String?) = apply { this.userUuid = userUuid }
        fun date(date: Date?) = apply { this.date = date }
        fun build() = Weight(
            userUuid,
            value,
            date
        )
    }
}