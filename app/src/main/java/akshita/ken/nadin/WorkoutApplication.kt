package akshita.ken.nadin

import akshita.ken.nadin.Data.Workout.WorkoutDatabase
import android.app.Application

class WorkoutApplication : Application() {

    //val called database of the type WorkoutDatabase. Instantiate the database instance by calling getDatabase() on WorkoutDatabase passing in the context.
    val database: WorkoutDatabase by lazy {
        WorkoutDatabase.getDatabase(this) }
}

