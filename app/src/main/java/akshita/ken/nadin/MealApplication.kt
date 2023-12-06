package akshita.ken.nadin

import akshita.ken.nadin.Data.Meal.MealDatabase
import android.app.Application

class MealApplication : Application() {
    val database: MealDatabase by lazy {
        MealDatabase.getDatabase(this)
    }
}