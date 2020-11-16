package small.app.beeroclock.db

import androidx.room.Entity
import org.jetbrains.annotations.NotNull

//city_ascii	lat	lng	country	iso3
@Entity(primaryKeys = ["name", "lat", "lng"])
data class City(
    @NotNull
    val name: String,
    @NotNull
    val lat: Double,
    @NotNull
    val lng: Double,
    @NotNull
    val country_code: String
) {
}