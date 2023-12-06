package akshita.ken.nadin

import akshita.ken.nadin.Data.Workout.Workout
import akshita.ken.nadin.Data.Workout.WorkoutDatabase
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditWorkoutViewModel : ViewModel() {

    //Live Data for current workout vals
    private val _currentWorkout = MutableLiveData<Workout>()
    val currentWorkout: LiveData<Workout> get() = _currentWorkout

    // LiveData to hold boolean val if the workout is found or not
    private val _workoutFound = MutableLiveData<Boolean>()
    val workoutFound: LiveData<Boolean> get() = _workoutFound

    // Search Workout in the database
    fun searchWorkout(searchQuery: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val workoutDatabase = WorkoutDatabase.getDatabase(context)
            val workoutDao = workoutDatabase.workoutDao()

            // Searching DB for search input entered and storing in searchResults
            val searchResults = workoutDao.searchWorkouts(searchQuery)

            if (searchResults.isNotEmpty()) {
                // If workout is found, update the currentWorkout LiveData
                _currentWorkout.postValue(searchResults[0])
            }
            // If workout is found or not found, return value
            _workoutFound.postValue(searchResults.isNotEmpty())
        }
    }

    fun updateWorkout(newWorkout: Workout, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val workoutDatabase = WorkoutDatabase.getDatabase(context)
            val workoutDao = workoutDatabase.workoutDao()

            // Update workout in DB
            workoutDao.updateWorkout(newWorkout)
        }
    }
}
