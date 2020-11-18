package small.app.beeroclock.db

import androidx.room.*

@Dao
interface CityDAO {

    @Query("SELECT * FROM City WHERE time_zone=:code")
    fun findCityInZone(code: String): List<City>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(vararg city: City)

    @Delete
    fun deleteCity(vararg city: City)

}