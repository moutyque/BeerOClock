package small.app.beeroclock

import java.util.*

fun getLocalHour(timeZone: String): Int {
    val instance = Calendar.getInstance(TimeZone.getTimeZone(timeZone))
    val hour = instance.get(Calendar.HOUR_OF_DAY)
    return (hour)
}