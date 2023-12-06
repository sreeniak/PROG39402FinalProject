package akshita.ken.nadin.Data.Meal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
class Meal (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,
    val mealName: String,
    val mealType: String,
    var calories: Int

)