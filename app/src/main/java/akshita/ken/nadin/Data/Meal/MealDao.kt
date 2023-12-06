package akshita.ken.nadin.Data.Meal

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MealDao {

    @Query("SELECT * FROM meal")
    fun getAllMeals(): LiveData<List<Meal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMeal(meal: Meal)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateMeal(meal: Meal)

    @Query("SELECT * FROM meal WHERE mealName LIKE :search")
    fun searchMeals(search: String): List<Meal>

    @Delete
    fun delete(meal: Meal)
}