package small.app.beeroclock.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class Zone(
    @NotNull @PrimaryKey(autoGenerate = true) val zone_id: Int,
    @NotNull val country_code: String,
    @NotNull val zone_name: String
) {
}