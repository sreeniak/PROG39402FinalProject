package akshita.ken.nadin

import akshita.ken.nadin.Data.Meal.Meal
import akshita.ken.nadin.Data.Workout.Workout
import akshita.ken.nadin.Data.Workout.WorkoutDao
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WorkoutViewModel(private val workoutDao: WorkoutDao) : ViewModel() {

    fun getAllWorkouts(): LiveData<List<Workout>> {
        return workoutDao.getAllWorkouts()
    }

    //, totalSets: Int
    fun addNewWorkout(date: String, totalSets: String, workoutType: String, distance: String) {
        val newWorkout = getNewWorkoutEntry(date, totalSets, workoutType, distance)
        insertWorkout(newWorkout)
    }

    private fun insertWorkout(workout: Workout) {
        viewModelScope.launch {
            workoutDao.addWorkout(workout)
        }
    }

    fun isEntryValid(
        date: String,
        totalSets: String,
        workoutType: String,
        distance: String
    ): Boolean {
        if (date.isBlank() || totalSets.toString().isBlank() || workoutType.toString()
                .isBlank() || distance.toString().isBlank()
        ) {
            return false
        }
        return true
    }

    private fun getNewWorkoutEntry(
        date: String,
        totalSets: String,
        workoutType: String,
        distance: String
    ): Workout {
        return Workout(
            date = date,
            totalSets = totalSets,
            workoutType = workoutType,
            distance = distance,
        )
    }

    fun deleteWorkout(workout: Workout) {
        viewModelScope.launch {
            workoutDao.deleteWorkout(workout)

        }
    }

//    fun deleteWorkoutByDate(date: String) {
//        viewModelScope.launch {
//            workoutDao.deleteWorkoutByDate(date)
//        }
//    }
//
//}

}
class WorkoutViewModelFactory(private val workoutDao: WorkoutDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkoutViewModel(workoutDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}