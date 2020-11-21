package small.app.beeroclock.db

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.zone.ZoneRulesException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class Repository(context: Context) {
    val db = getInstance(context)
    val validZones: MutableList<String> = ArrayList()
    val validCities: MutableMap<City, String> = HashMap()
    private val tag = "Repository"
    private val availableIDs = TimeZone.getAvailableIDs()
    var currentTime = -1
    suspend fun getCities() {


        withContext(Dispatchers.IO) {
            if (currentTime != Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
                currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                //TODO : Manage the availabelIds based on RAW offset + dst

                availableIDs.forEach {
                    //Some time zone are present in TimeZone but not usable by LocalDateTime class
                    try {


                        val hour = Calendar.getInstance(TimeZone.getTimeZone(it))
                            .get(Calendar.HOUR_OF_DAY)
                        if (hour == 18) {
                            db.cityDAO().findCityInZone(it).forEach {
                                validCities.put(it, db.countryDAO().findCountryName(it.county_code))
                            }
                        }
                    } catch (e: ZoneRulesException) {
                        Log.e(tag, "The time zone $it do not exist")
                    }
                }
            }
        }


    }

}