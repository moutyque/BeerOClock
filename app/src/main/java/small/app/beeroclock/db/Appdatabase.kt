package small.app.beeroclock.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = arrayOf(Country::class, Timezone::class, Zone::class, City::class),
    version = 1,
    exportSchema = true
)


abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDAO(): CountryDAO
    abstract fun timezoneDAO(): TimezoneDAO
    abstract fun zoneDAO(): ZoneDAO
    abstract fun cityDAO(): CityDAO

}

lateinit var _context: Context

@Volatile
private lateinit var INSTANCE: AppDatabase
fun getInstance(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            _context = context
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).createFromAsset("beeroclock.db")
                .fallbackToDestructiveMigration()
                .build()
            //.addCallback(rdc)

        }
        return INSTANCE;
    }
}

