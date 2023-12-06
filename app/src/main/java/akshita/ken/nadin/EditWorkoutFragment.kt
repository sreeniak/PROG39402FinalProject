package akshita.ken.nadin

import akshita.ken.nadin.Data.Workout.Workout
import akshita.ken.nadin.databinding.FragmentEditWorkoutBinding
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class EditWorkoutFragment : Fragment() {

    // ViewModel Declaration
    private lateinit var viewModel: EditWorkoutViewModel
    // View Binding Declaration
    private lateinit var binding: FragmentEditWorkoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[EditWorkoutViewModel::class.java]

        // On Click listeners for both update and search buttons
        binding.searchButton.setOnClickListener { onSearchButtonClick() }
        binding.updateButton.setOnClickListener { onUpdateButtonClick() }

        // LiveData observer for workout details
        viewModel.currentWorkout.observe(viewLifecycleOwner) { workout ->
            binding.editTextDate.setText(workout.date)
            binding.editTextWorkoutType.setText(workout.workoutType)
            binding.editTextSets.setText(workout.totalSets)
            binding.editTextDistance.setText(workout.distance)
        }

        // LiveData observer for error message if workout not found
        viewModel.workoutFound.observe(viewLifecycleOwner) { workoutFound ->
            if (workoutFound) {
                Toast.makeText(requireContext(), "Workout Found.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Workout Not Found.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to handle search button click
    private fun onSearchButtonClick() {
        val searchQuery = binding.searchInput.text.toString()
        if (searchQuery.isNotEmpty()) {
            // ViewModel function for search
            viewModel.searchWorkout(searchQuery, requireContext())
        } else {
            Toast.makeText(requireContext(), "Please enter a workout", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to handle update button click
    private fun onUpdateButtonClick() {
        // Retrieving values for the update
        val date = binding.editTextDate.text.toString()
        val workoutType = binding.editTextWorkoutType.text.toString()
        val totalSets = binding.editTextSets.text.toString()
        val distance = binding.editTextDistance.text.toString()

        // Using the workout ID for the current workout
        val currentWorkoutId = viewModel.currentWorkout.value?.id ?: 0

        val newWorkout = Workout(
            id = currentWorkoutId,
            date = date,
            workoutType = workoutType,
            totalSets = totalSets,
            distance = distance
        )
        viewModel.updateWorkout(newWorkout, requireContext())
        Toast.makeText(requireContext(), "Workout Updated.", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_editWorkoutFragment_to_workoutListFragment)
    }
}
