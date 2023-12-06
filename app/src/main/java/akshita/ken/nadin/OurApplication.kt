package akshita.ken.nadin

import akshita.ken.nadin.Data.Meal.MealDatabase
import akshita.ken.nadin.Data.Workout.WorkoutDatabase
import android.app.Application

class OurApplication : Application() {
    val mealDatabase: MealDatabase by lazy {
        MealDatabase.getDatabase(this)
    }

    val workoutDatabase: WorkoutDatabase by lazy {
        WorkoutDatabase.getDatabase(this)
    }
}