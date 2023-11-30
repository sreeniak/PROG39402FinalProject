package akshita.ken.nadin.Data.Workout

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Workout::class], version = 2, exportSchema = false)
abstract class WorkoutDatabase : RoomDatabase() {
        abstract fun WorkoutDao(): WorkoutDao

        companion object {
            @Volatile
            private var INSTANCE: WorkoutDatabase? = null

            fun getDatabase(context: Context): WorkoutDatabase {

                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        WorkoutDatabase::class.java,
                        "workouts"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }
//test commit
