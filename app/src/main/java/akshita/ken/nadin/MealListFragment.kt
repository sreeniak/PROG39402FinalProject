package akshita.ken.nadin

import akshita.ken.nadin.Data.Meal.Meal
import akshita.ken.nadin.Data.Meal.MealDao
import akshita.ken.nadin.Data.Meal.MealDatabase
import akshita.ken.nadin.databinding.FragmentMealListBinding
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MealListFragment : Fragment() {
    private var _binding: FragmentMealListBinding? = null
    private val binding get() = _binding!!

    private lateinit var mealDao: MealDao

    //for dynamic list
//    private lateinit var viewModel: MealViewModel
    private val viewModel: MealViewModel by activityViewModels {
        MealViewModelFactory(
            (requireActivity().application as OurApplication).mealDatabase.mealDao()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMealListBinding.inflate(inflater, container, false)
        mealDao = MealDatabase.getDatabase(requireContext()).mealDao()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = ViewModelProvider(this).get(MealViewModel::class.java)

        viewModel.getAllMeals().observe(viewLifecycleOwner) { meals ->
            updateRecyclerView(meals)
        }

        binding.recycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleView.setHasFixedSize(true)

        binding.addMealBtn.setOnClickListener{
            findNavController().navigate(R.id.action_mealListFragment_to_addMealFragment)
        }

        binding.delete.setOnClickListener {
            val mealNameToSearch = binding.searchInput.text.toString()

            lifecycleScope.launch {
                val searchResults = withContext(Dispatchers.IO) {
                    mealDao.searchMeals("%$mealNameToSearch%")
                }

                 if (searchResults.isNotEmpty()) {
                    for (meal in searchResults) {
                        Toast.makeText(
                            context,
                            "Meal Deleted: ${meal.mealName}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                     Toast.makeText(
                         context,
                         "Meal Not Found",
                         Toast.LENGTH_LONG
                     ).show()
                }

                for (meal in searchResults) {
                    withContext(Dispatchers.IO) {
                        mealDao.delete(meal)
                    }
                }
            }
            hideKeyboard()
            binding.searchInput.text.clear()
        }

        binding.edit.setOnClickListener{
            findNavController().navigate(R.id.action_mealListFragment_to_editMealFragment)
        }

    }

    // For user interaction, dismiss keyboard after button is pressed
    private fun hideKeyboard() {
        val inputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
    private fun updateRecyclerView(meals: List<Meal>) {
        val listMeals = meals.map { meal ->
            ListMeal(
                text1 = "Date: " + meal.date,
                text2 = "Meal Name: " + meal.mealName,
                text3 = "Meal Type: " + meal.mealType,
                text4 = meal.calories
            )
        }

        // Pass the list of ListMeal items to the adapter
        val mealAdapter = MealRecyclerView(listMeals)
        binding.recycleView.adapter = mealAdapter
    }

}