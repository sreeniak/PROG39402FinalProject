package akshita.ken.nadin.Data.Workout

import akshita.ken.nadin.Data.Workout.Workout
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

    @Query("SELECT * FROM workout WHERE workoutType LIKE :search")
    fun searchWorkouts(search: String): List<Workout>

    @Delete
    fun delete(workout: Workout)

    @Query("DELETE FROM workout WHERE date = :date")
    suspend fun deleteWorkoutByDate(date: String)

}