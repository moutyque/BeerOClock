package small.app.beeroclock.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class Country(
    @NotNull @PrimaryKey val country_code: String,
    @NotNull val country_name: String
) {
}