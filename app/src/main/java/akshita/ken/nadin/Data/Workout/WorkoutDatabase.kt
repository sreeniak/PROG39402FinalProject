package akshita.ken.nadin.Data.Workout

import akshita.ken.nadin.WorkoutApplication
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Workout::class], version = 2, exportSchema = false)
abstract class WorkoutDatabase : RoomDatabase() {
        abstract fun workoutDao(): WorkoutDao

        companion object {
            @Volatile
            private var INSTANCE: WorkoutDatabase? = null

            fun getDatabase(context: Context): WorkoutDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        //avoids potential memory leaks
                        context.applicationContext,
                        WorkoutDatabase::class.java,
                        "workout_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
}
