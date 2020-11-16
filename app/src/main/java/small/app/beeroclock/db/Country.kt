package small.app.beeroclock.db

import androidx.room.Entity
import androidx.room.Index
import org.jetbrains.annotations.NotNull

@Entity(
    indices = [Index(value = ["country_code"])],
    primaryKeys = ["country_code"]
)
data class Country(
    @NotNull
    val country_code: String,
    @NotNull val country_name: String
) {


}