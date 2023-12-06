package akshita.ken.nadin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WorkoutRecycleView (private val WorkoutList:List<WorkoutList>): RecyclerView.Adapter<WorkoutRecycleView.MyViewHolder>() {
    class MyViewHolder(workoutView: View): RecyclerView.ViewHolder(workoutView){

        val workoutDate: TextView = workoutView.findViewById(R.id.textViewWDate)
        val workoutTotalSets: TextView = workoutView.findViewById(R.id.textViewWTotalSets)
        val workoutType: TextView = workoutView.findViewById(R.id.textViewWType)
        val workoutDistance: TextView = workoutView.findViewById(R.id.textViewWDistance)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutRecycleView.MyViewHolder {
        val workoutView = LayoutInflater.from(parent.context).inflate(R.layout.workout_item, parent, false)
        return MyViewHolder(workoutView)
    }

    override fun onBindViewHolder(holder: WorkoutRecycleView.MyViewHolder, position: Int) {

        val currentWorkout = WorkoutList[position]
        holder.workoutDate.text = currentWorkout.date
        holder.workoutTotalSets.text = currentWorkout.totalSets.toString()
        holder.workoutType.text = currentWorkout.workoutType
        holder.workoutDistance.text = currentWorkout.distance.toString()

    }

    override fun getItemCount() =  WorkoutList.size
}