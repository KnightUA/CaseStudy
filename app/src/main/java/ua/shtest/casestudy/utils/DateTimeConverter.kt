package ua.shtest.casestudy.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

object DateTimeConverter {
    fun String.convertServerDateToLocal(): Date? {
        val parser = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
        return parser.parse(this)
    }
}