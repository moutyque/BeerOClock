package small.app.beeroclock.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TimezoneDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTimezone(vararg timezone: Timezone)

    @Query("SELECT * FROM Timezone")
    fun findAll(): List<Timezone>

    @Query("SELECT DISTINCT zone_id FROM Timezone WHERE gmt_offset=:offset")
    fun findZoneIdByOffset(offset: Int): List<Int>


    @Query("SELECT DISTINCT country_code FROM Zone WHERE zone_name=:name")
    fun findCountryCodeInZone(name: String): List<String>

}