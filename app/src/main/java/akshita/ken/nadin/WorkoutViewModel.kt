package akshita.ken.nadin

import akshita.ken.nadin.Data.Workout.Workout
import akshita.ken.nadin.Data.Workout.WorkoutDao
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class WorkoutViewModel(private val workoutDao: WorkoutDao) : ViewModel() {

    val allWorkouts: LiveData<List<Workout>> = workoutDao.getAllWorkouts()


    suspend fun addWorkout(workout: Workout) {
        workoutDao.addWorkout(workout)
    }

    suspend fun updateWorkout(workout: Workout) {
        workoutDao.updateWorkout(workout)
    }

    suspend fun deleteWorkout(workout: Workout) {
        workoutDao.deleteWorkout(workout)
    }
}