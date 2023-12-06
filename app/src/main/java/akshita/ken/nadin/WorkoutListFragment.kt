package akshita.ken.nadin

import akshita.ken.nadin.Data.Meal.Meal
import akshita.ken.nadin.Data.Workout.Workout
import akshita.ken.nadin.databinding.FragmentMealListBinding
import akshita.ken.nadin.databinding.FragmentWorkoutListBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

class WorkoutListFragment : Fragment() {
    private var _binding: FragmentWorkoutListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WorkoutViewModel by activityViewModels {
        WorkoutViewModelFactory(
            (requireActivity().application as OurApplication).workoutDatabase.workoutDao()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWorkoutListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getAllWorkouts().observe(viewLifecycleOwner) { workouts ->
            updateRecyclerView(workouts)
        }

        binding.recycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleView.setHasFixedSize(true)

        binding.addworkoutBtn.setOnClickListener{
            findNavController().navigate(R.id.action_workoutListFragment_to_addWorkoutFragment)
        }
    }
    private fun updateRecyclerView(workouts: List<Workout>) {
        val listWorkouts = workouts.map { workout ->
            WorkoutList(
                date = "Date: " + workout.date,
                workoutType = workout.workoutType,
                totalSets = "Total Sets: ${workout.totalSets}",
                distance = "Distance: ${workout.distance}"
            )
        }

        val workoutAdapter = WorkoutRecycleView(listWorkouts, viewModel)
        binding.recycleView.adapter = workoutAdapter
        workoutAdapter.notifyDataSetChanged()


//        val workoutAdapter = WorkoutRecycleView(listWorkouts)
//        binding.recycleView.adapter = workoutAdapter
//        workoutAdapter.notifyDataSetChanged()
    }

}