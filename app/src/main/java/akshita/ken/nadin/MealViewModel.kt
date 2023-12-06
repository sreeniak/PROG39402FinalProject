package akshita.ken.nadin

import akshita.ken.nadin.Data.Meal.Meal
import akshita.ken.nadin.Data.Meal.MealDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MealViewModel (private val mealDao: MealDao): ViewModel() {

    fun addNewMeal(mealName: String, calories: Int, date: String, mealType: String){
        val newMeal = getNewMealEntry(mealName, calories, date, mealType)
        insertMeal(newMeal)
    }

    private fun insertMeal(meal: Meal) {
        //to interact with DB off main thread, start a coroutine and call DAO method
        //viewModelScope.launch starts a coroutine in the ViewModelScope
        viewModelScope.launch {//suspend functions only callable from a coroutine or another suspend function
            mealDao.addMeal(meal) //this is a suspend function
        }
    }

    fun isEntryValid(mealName: String, calories: Int, date: String, mealType: String): Boolean {
        if (mealName.isBlank() || calories < 0 || date.isBlank() || mealType.isBlank()) {
            return false
        }
        return true
    }

    private fun getNewMealEntry(mealName: String, calories: Int, date: String, mealType: String): Meal {
        return Meal(
            mealName = mealName,
            calories = calories,
            date = date,
            mealType = mealType
        )
    }
}

class MealViewModelFactory(private val mealDao: MealDao) : ViewModelProvider.Factory {
    //Override create() method. If the modelClass is the same as the MealViewModel class
    //return an instance of it, else throw an exception
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MealViewModel(mealDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}