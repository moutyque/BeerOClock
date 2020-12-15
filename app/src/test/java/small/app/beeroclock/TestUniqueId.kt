package small.app.beeroclock

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


@RunWith(Parameterized::class)
class TestUniqueId(private val id: String) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf("America/Asuncion"),
                arrayOf("America/Sao_Paulo")

            )
        }

        fun list() = listOf<String>(
            "America/Asuncion", "America/Sao_Paulo"
        )
    }

    @Test
    fun testOneId() {
        val tz = TimeZone.getTimeZone(id)
        val df: DateFormat = SimpleDateFormat("HH")
        df.setTimeZone(tz) // strip timezone

        Assert.assertEquals(id, Integer.parseInt(df.format(Date())), getLocalHour(id))
    }
}