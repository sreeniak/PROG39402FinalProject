package akshita.ken.nadin.Data.Workout

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workout")
    fun getAllWorkouts(): LiveData<List<Workout>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWorkout(workout: Workout)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateWorkout(workout: Workout)

    @Delete
    suspend fun deleteWorkout(workout: Workout)

    @Query("DELETE FROM workout WHERE date = :date")
    suspend fun deleteWorkoutByDate(date: String)

}