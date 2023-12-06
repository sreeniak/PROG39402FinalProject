package akshita.ken.nadin

import akshita.ken.nadin.Data.Meal.Meal
import akshita.ken.nadin.Data.Meal.MealDatabase
import akshita.ken.nadin.Data.Workout.Workout
import akshita.ken.nadin.Data.Workout.WorkoutDao
import akshita.ken.nadin.Data.Workout.WorkoutDatabase
import akshita.ken.nadin.databinding.FragmentMealListBinding
import akshita.ken.nadin.databinding.FragmentWorkoutListBinding
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.internal.ViewUtils.hideKeyboard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WorkoutListFragment : Fragment() {
    private var _binding: FragmentWorkoutListBinding? = null
    private val binding get() = _binding!!

    private lateinit var workoutDao: WorkoutDao

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
        workoutDao = WorkoutDatabase.getDatabase(requireContext()).workoutDao()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getAllWorkouts().observe(viewLifecycleOwner) { workouts ->
            updateRecyclerView(workouts)
        }

        binding.recycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleView.setHasFixedSize(true)

        binding.addWorkoutBtn.setOnClickListener{
            findNavController().navigate(R.id.action_workoutListFragment_to_addWorkoutFragment)
        }

        binding.delete.setOnClickListener {
            val workoutNameToSearch = binding.searchInput.text.toString()

            lifecycleScope.launch {
                val searchResults = withContext(Dispatchers.IO) {
                    workoutDao.searchWorkouts("%$workoutNameToSearch%")
                }

                if (searchResults.isNotEmpty()) {
                    for (workout in searchResults) {
                        Toast.makeText(
                            context,
                            "Workout Deleted: ${workout.workoutType}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Workout Not Found",
                        Toast.LENGTH_LONG
                    ).show()
                }

                for (workout in searchResults) {
                    withContext(Dispatchers.IO) {
                        workoutDao.delete(workout)
                    }
                }
            }
            hideKeyboard()
            binding.searchInput.text.clear()
        }
    }
    // For user interaction, dismiss keyboard after button is pressed
    private fun hideKeyboard() {
        val inputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
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

        val workoutAdapter = WorkoutRecycleView(listWorkouts)
        binding.recycleView.adapter = workoutAdapter
        workoutAdapter.notifyDataSetChanged()


//        val workoutAdapter = WorkoutRecycleView(listWorkouts)
//        binding.recycleView.adapter = workoutAdapter
//        workoutAdapter.notifyDataSetChanged()
    }

}