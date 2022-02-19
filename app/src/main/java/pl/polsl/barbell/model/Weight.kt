package pl.polsl.barbell.model

import java.util.*

/**
 * Weight
 *
 * @property userUuid
 * @property value
 * @property date
 * @constructor Create empty Weight
 */
class Weight(
    val userUuid: String? = null,
    var value: Double? = null,
    var date: Date? = null
) {
    /**
     * Builder
     *
     * @property userUuid
     * @property value
     * @property date
     * @constructor Create empty Builder
     */
    data class Builder(
        private var userUuid: String? = null,
        private var value: Double? = null,
        private var date: Date? = null
    ) {
        /**
         * Value
         *
         * @param value
         */
        fun value(value: Double?) = apply { this.value = value }

        /**
         * Value
         *
         * @param userUuid
         */
        fun value(userUuid: String?) = apply { this.userUuid = userUuid }

        /**
         * Date
         *
         * @param date
         */
        fun date(date: Date?) = apply { this.date = date }

        /**
         * Build
         *
         */
        fun build() = Weight(
            userUuid,
            value,
            date
        )
    }
}