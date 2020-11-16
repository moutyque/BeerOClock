package small.app.beeroclock.db

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import kotlin.math.abs


class Repository(context: Context) {
    val db = getInstance(context)

    val tag = "Repository"
    private val DATE_FORMAT = "dd-M-yyyy hh:mm:ss a"

    suspend fun getCities() {


        //TODO : issue with the offset need to find a way to get the negative or positive
        withContext(Dispatchers.IO) {

            val validZone: MutableList<String> = ArrayList<String>()
            val validCountryCode: MutableCollection<String> = ArrayList<String>()
            val validCountryName: MutableCollection<String> = ArrayList<String>()

            val rightNow = Calendar.getInstance()

            val currentHourIn24Format =
                rightNow[Calendar.HOUR_OF_DAY] // return the hour in 24 hrs format (ranging from 0-23)
            Log.d(tag, currentHourIn24Format.toString())
            val offset =
                abs(18 - currentHourIn24Format) * 3_600 + (TimeZone.getDefault().dstSavings / 1000)
            Log.d(tag, "Offset : " + offset)
            val zoneId = db.timezoneDAO().findZoneIdByOffset(offset)
            zoneId.forEach {
                Log.d(tag, "zoneId : " + it.toString())
                val findZoneFromId = db.zoneDAO().findZoneFromId(it)
                findZoneFromId.forEach {
                    Log.d(tag, "findZoneFromId : " + it.toString())
                    val hour = LocalDateTime.now(ZoneId.of(it.zone_name)).hour
                    if (hour == 18) {
                        validZone.add(it.zone_name)
                    }
                }
            }
            validZone.forEach {
                validCountryCode.addAll(db.timezoneDAO().findCountryCodeInZone(it))
            }

            validCountryCode.forEach {
                validCountryName.add(db.countryDAO().findNameFromCode(it))
            }

            //TODO : add city search
            validCountryName.forEach {
                Log.d(tag, it)
            }


        }


    }

}