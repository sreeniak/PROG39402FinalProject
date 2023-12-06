package akshita.ken.nadin

import akshita.ken.nadin.Data.Meal.Meal
import akshita.ken.nadin.databinding.FragmentMealListBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

class MealListFragment : Fragment() {
    private var _binding: FragmentMealListBinding? = null
    private val binding get() = _binding!!

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
    }
    private fun updateRecyclerView(meals: List<Meal>) {
        val listMeals = meals.map { meal ->
            ListMeal(
                text1 = "Date: " + meal.date,
                text2 = meal.mealName,
                text3 = meal.mealType,
                text4 = meal.calories
            )
        }

        // Pass the list of ListMeal items to the adapter
        val mealAdapter = MealRecyclerView(listMeals)
        binding.recycleView.adapter = mealAdapter
    }

}