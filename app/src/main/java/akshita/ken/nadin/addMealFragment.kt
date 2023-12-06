package akshita.ken.nadin

import akshita.ken.nadin.Data.Meal.Meal
import akshita.ken.nadin.databinding.FragmentAddMealBinding
import akshita.ken.nadin.databinding.FragmentHomeBinding
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class addMealFragment : Fragment() {
    lateinit var meal: Meal

    //binding object instance correpsonding to the fragment_add_meal.xml layout.
    //this property is non null between the onCreateView and onDestroyView() lifecycle callbacks,
    //when the view hierarchy is attached to the fragment
    private var _binding: FragmentAddMealBinding? = null
    private val binding get() = _binding!!

    //viewmodel instance for communication between fragments and activities
    private val viewModel: MealViewModel by activityViewModels {
        MealViewModelFactory(
            (requireActivity().application as MealApplication).database.mealDao()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.editTextMealName.text.toString(),
            binding.editTextCalories.text.toString().toInt(),
            binding.editTextDate.text.toString(),
            binding.editTextMealType.text.toString()
        )
    }

    private fun addNewMeal() {
        if (isEntryValid()){
            viewModel.addNewMeal(
                binding.editTextMealName.text.toString(),
                binding.editTextCalories.text.toString().toInt(),
                binding.editTextDate.text.toString(),
                binding.editTextMealType.text.toString()
            )
        }
        //nav back to Meal List Frag after adding a new meal
        findNavController().navigate(R.id.action_addMealFragment_to_mealListFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddMeal.setOnClickListener {
            // Call the function to add a new meal when the button is clicked
            addNewMeal()
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