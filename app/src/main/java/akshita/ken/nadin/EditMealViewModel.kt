package akshita.ken.nadin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import akshita.ken.nadin.Data.Meal.Meal
import akshita.ken.nadin.Data.Meal.MealDatabase
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditMealViewModel : ViewModel() {

    //Live Data for current meal vals
    private val _currentMeal = MutableLiveData<Meal>()
    val currentMeal: LiveData<Meal> get() = _currentMeal

    // LiveData to hold boolean val if the meal is found or not
    private val _mealFound = MutableLiveData<Boolean>()
    val mealFound: LiveData<Boolean> get() = _mealFound

    // Search Meal in the database
    fun searchMeal(searchQuery: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val mealDatabase = MealDatabase.getDatabase(context)
            val mealDao = mealDatabase.mealDao()

            // Searching DB for search input entered and storing in searchResults
            val searchResults = mealDao.searchMeals(searchQuery)

            if (searchResults.isNotEmpty()) {
                // If meal is found, update the currentMeal LiveData
                _currentMeal.postValue(searchResults[0])
            }
            // If meal is found, return value
            _mealFound.postValue(searchResults.isNotEmpty())
        }
    }

    fun updateMeal(newMeal: Meal, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val mealDatabase = MealDatabase.getDatabase(context)
            val mealDao = mealDatabase.mealDao()

            // Update meal in DB
            mealDao.updateMeal(newMeal)
        }
    }
}