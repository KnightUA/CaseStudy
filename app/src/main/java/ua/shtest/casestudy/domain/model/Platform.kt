package ua.shtest.casestudy.domain.model

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

enum class Platform(private val modelName: String? = null) {
    Unknown, SercommG450("Sercomm G450"),
    SercommG550("Sercomm G550"),
    SercommNA900("Sercomm NA900"),
    SercommNA301("Sercomm NA301"),
    SercommNA930("Sercomm NA930"),
    MiCasaVerdeVeraLite("MiCasaVerde VeraLite");

    companion object {
        fun createFrom(modelName: CharSequence?): Platform =
            values().firstOrNull { it.modelName.contentEquals(modelName, ignoreCase = true) }
                ?: Unknown
    }
}

