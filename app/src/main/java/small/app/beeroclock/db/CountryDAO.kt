package small.app.beeroclock.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CountryDAO {

    @Insert
    fun insertCountry(country: Country)

    @Delete
    fun deleteCountry(country: Country)


    @Query("SELECT country_name FROM Country WHERE country_code=:code")
    fun findCountryName(code: String): String
}