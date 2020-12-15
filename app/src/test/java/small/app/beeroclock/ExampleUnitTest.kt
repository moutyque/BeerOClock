package small.app.beeroclock

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.ZoneId
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Test
    fun testCurrentTimeZoneWithZoneId() {

        val availableZoneIds = ZoneId.getAvailableZoneIds()
        availableZoneIds.forEach {
            val tz = TimeZone.getTimeZone(it)
            val df: DateFormat = SimpleDateFormat("HH")
            df.setTimeZone(tz) // strip timezone

            assertEquals(it, Integer.parseInt(df.format(Date())), getLocalHour(it))
            val zi = ZoneId.of(it)
            val now = LocalTime.now(zi)
            assertEquals(it, now.hour, getLocalHour(it))


        }

    }

    @Test
    fun testCurrentTimeZoneWithTimeZone() {
        val availableIDs = TimeZone.getAvailableIDs()

        availableIDs.forEach {
            val tz = TimeZone.getTimeZone(it)
            val df: DateFormat = SimpleDateFormat("HH")
            df.setTimeZone(tz) // strip timezone

            assertEquals(it, Integer.parseInt(df.format(Date())), getLocalHour(it))
            try {
                val zi = ZoneId.of(it)
                val now = LocalTime.now(zi)
                assertEquals(it, now.hour, getLocalHour(it))
            } catch (e: Exception) {

            }


        }

    }

}