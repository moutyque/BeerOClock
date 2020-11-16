package small.app.beeroclock.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ZoneDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertZone(vararg zone: Zone)

    @Query("SELECT * FROM Zone")
    fun findAll(): List<Zone>

    @Query("SELECT * FROM Zone WHERE zone_id=:in_zone_id")
    fun findZoneFromId(in_zone_id: Int): List<Zone>
}