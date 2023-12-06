package akshita.ken.nadin

import akshita.ken.nadin.Data.Workout.Workout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WorkoutRecycleView(
    private val workoutList: List<WorkoutList>) : RecyclerView.Adapter<WorkoutRecycleView.MyViewHolder>() {

    class MyViewHolder(workoutView: View) : RecyclerView.ViewHolder(workoutView) {

        val workoutDate: TextView = workoutView.findViewById(R.id.textViewWDate)
        val workoutTotalSets: TextView = workoutView.findViewById(R.id.textViewWTotalSets)
        val workoutType: TextView = workoutView.findViewById(R.id.textViewWType)
        val workoutDistance: TextView = workoutView.findViewById(R.id.textViewWDistance)
       // val deleteBtn: ImageView = workoutView.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WorkoutRecycleView.MyViewHolder {
        val workoutView =
            LayoutInflater.from(parent.context).inflate(R.layout.workout_item, parent, false)
        return MyViewHolder(workoutView)
    }

    override fun onBindViewHolder(holder: WorkoutRecycleView.MyViewHolder, position: Int) {
        val currentWorkout = workoutList[position]

        holder.workoutDate.text = currentWorkout.date
        holder.workoutTotalSets.text = currentWorkout.totalSets
        holder.workoutType.text = currentWorkout.workoutType
        holder.workoutDistance.text = currentWorkout.distance
    }

//        holder.deleteBtn.setOnClickListener {
//            val workoutToDelete = workoutList[position]
//            viewModel.deleteWorkoutByDate(workoutToDelete.date)// Assuming you delete by date
//        }
//    }


//        // Convert WorkoutList object to Workout object
//        val workout = Workout(
//           // id = currentWorkout.id,
//            date = currentWorkout.date,
//            workoutType = currentWorkout.workoutType,
//            totalSets = currentWorkout.totalSets,
//            distance = currentWorkout.distance
//        )
//
//        // Pass the workout object to the deleteWorkout() method
//        holder.deleteBtn.setOnClickListener {
//            deleteWorkout(workout)
//        }
//    }

//    private fun deleteWorkout(workout: Workout) {
//        // Pass the workout to ViewModel for deletion
//        // Ensure your ViewModel has a function to delete a workout
//        viewModel.deleteWorkout(workout)
//    }

    override fun getItemCount() = workoutList.size

}
