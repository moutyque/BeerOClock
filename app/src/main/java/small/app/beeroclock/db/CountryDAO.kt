package small.app.beeroclock.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountry(vararg country: Country)

    @Query("SELECT * FROM Country")
    fun findAll(): List<Country>

    @Query("SELECT country_name FROM Country WHERE country_code=:code")
    fun findNameFromCode(code: String): String

}