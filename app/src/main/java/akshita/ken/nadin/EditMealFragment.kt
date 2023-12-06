package akshita.ken.nadin

import akshita.ken.nadin.Data.Meal.Meal
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import akshita.ken.nadin.databinding.FragmentEditMealBinding
import androidx.navigation.fragment.findNavController

class EditMealFragment : Fragment() {

    // ViewModel Declaration
    private lateinit var viewModel: EditMealViewModel

    // View Binding Declaration
    private lateinit var binding: FragmentEditMealBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[EditMealViewModel::class.java]

        // On Click listeners for both update and search buttons
        binding.searchButton.setOnClickListener { onSearchButtonClick() }
        binding.updateButton.setOnClickListener { onUpdateButtonClick() }

        // LiveData observer for meal details
        viewModel.currentMeal.observe(viewLifecycleOwner) { meal ->
            binding.editTextDate.setText(meal.date)
            binding.editTextMealName.setText(meal.mealName)
            binding.editTextMealType.setText(meal.mealType)
            binding.editTextCalories.setText(meal.calories.toString())
        }

        // LiveData observer for error message if meal not found
        viewModel.mealFound.observe(viewLifecycleOwner) { mealFound ->
            if (mealFound) {
                Toast.makeText(requireContext(), "Meal Found.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Meal Not Found.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to handle search button click
    private fun onSearchButtonClick() {
        val searchQuery = binding.searchInput.text.toString()
        if (searchQuery.isNotEmpty()) {
            // ViewModel function for search
            viewModel.searchMeal(searchQuery, requireContext())
        } else {
            Toast.makeText(requireContext(), "Please enter a meal", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to handle update button click, while also checking if calories are null
    private fun onUpdateButtonClick() {
        // Retrieving values for the update
        val date = binding.editTextDate.text.toString()
        val mealName = binding.editTextMealName.text.toString()
        val mealType = binding.editTextMealType.text.toString()
        val calories = binding.editTextCalories.text.toString().toIntOrNull()

        // Using the meal ID for the current meal
        val currentMealId = viewModel.currentMeal.value?.id ?: 0

        if (calories != null) {
            val newMeal = Meal(
                id = currentMealId,
                date = date,
                mealName = mealName,
                mealType = mealType,
                calories = calories
            )

            viewModel.updateMeal(newMeal, requireContext())
            Toast.makeText(requireContext(), "Meal Updated", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Please enter calories", Toast.LENGTH_SHORT).show()
        }

        findNavController().navigate(R.id.action_editMealFragment_to_mealListFragment)
    }
}