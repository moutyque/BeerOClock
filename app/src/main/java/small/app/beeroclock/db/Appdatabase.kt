package small.app.beeroclock.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = arrayOf(City::class, Country::class),
    version = 1,
    exportSchema = true
)


abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDAO(): CityDAO
    abstract fun countryDAO(): CountryDAO
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

        }
        return INSTANCE
    }
}

