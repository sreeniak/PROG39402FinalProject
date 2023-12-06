package akshita.ken.nadin

import akshita.ken.nadin.Data.Workout.Workout
import akshita.ken.nadin.databinding.FragmentAddWorkoutBinding
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class AddWorkoutFragment : Fragment() {

    lateinit var workout: Workout


    private val viewModel: WorkoutViewModel by activityViewModels {
        WorkoutViewModelFactory(
            (activity?.application as OurApplication).workoutDatabase.workoutDao()
        )
    }

    private var _binding: FragmentAddWorkoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("AddWorkoutFragment", "onCreateView")
        _binding = FragmentAddWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.editTextDate.text.toString(),
            binding.editTextTotalSets.text.toString().toInt(),
            binding.editTextWorkoutType.text.toString(),
            binding.editTextDistance.text.toString().toDouble(),
        )
    }

    private fun addNewWorkout() {
        if (isEntryValid()) {
            viewModel.addNewWorkout(
                binding.editTextDate.text.toString(),
                binding.editTextTotalSets.text.toString().toInt(),
                binding.editTextWorkoutType.text.toString(),
                binding.editTextDistance.text.toString().toDouble(),
            )

        }
        findNavController().navigate(R.id.action_addWorkoutFragment_to_workoutListFragment)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddWorkout.setOnClickListener {
            addNewWorkout()
        }
    }

    //  Called before fragment destroyed
    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }


}