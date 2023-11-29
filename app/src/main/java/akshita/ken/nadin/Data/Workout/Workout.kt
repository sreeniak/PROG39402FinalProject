package akshita.ken.nadin.Data.Workout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,
    val workoutType: String,
    val totalSets: Int, //dunno if we wanna include this
    val distance: Double,
)
