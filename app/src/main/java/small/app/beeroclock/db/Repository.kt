package small.app.beeroclock.db

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.zone.ZoneRulesException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class Repository(context: Context) {
    val db = getInstance(context)
    val validZones: MutableList<String> = ArrayList()
    val validCities: MutableMap<City, String> = HashMap()
    private val tag = "Repository"
    private val DATE_FORMAT = "dd-M-yyyy hh:mm:ss a"

    suspend fun getCities() {


        withContext(Dispatchers.IO) {


            //TODO : Manage the availabelIds based on RAW offset + dst
            val availableIDs = TimeZone.getAvailableIDs()

            availableIDs.forEach {
                Log.d(tag, "findZoneFromId : " + it.toString())
                //Some time zone are present in TimeZone but not usable by LocalDateTime class
                try {
                    val hour = LocalDateTime.now(ZoneId.of(it)).hour
                    if (hour == 18) {
                        validZones.add(it)
                    }
                } catch (e: ZoneRulesException) {

                }


            }


            //TODO : Find the city in each time zone, do not relly on country cause some like Russia have multiple time zone
            validZones.forEach {
                db.cityDAO().findCityInZone(it).forEach {
                    validCities.put(it, db.countryDAO().findCountryName(it.county_code))
                }
            }


        }


    }

}