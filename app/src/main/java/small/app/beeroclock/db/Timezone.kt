package small.app.beeroclock.db

import androidx.room.Entity
import androidx.room.Index
import org.jetbrains.annotations.NotNull

@Entity(
    indices = [Index(value = ["zone_id", "time_start"])],
    primaryKeys = ["zone_id", "time_start"]
)
data class Timezone(
    @NotNull
    val zone_id: Int,
    @NotNull
    val abbreviation: String,
    @NotNull
    val time_start: Double,
    @NotNull
    val gmt_offset: Int,
    @NotNull
    val dst: Char
) {
}