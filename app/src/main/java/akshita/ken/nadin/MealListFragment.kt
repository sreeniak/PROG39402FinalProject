package akshita.ken.nadin

import akshita.ken.nadin.databinding.FragmentMealListBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MealListFragment : Fragment() {
    private var _binding: FragmentMealListBinding? = null
    private val binding get() = _binding!!

    //for dynamic list
    private lateinit var viewModel: MealViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMealListBinding.inflate(inflater, container, false)
        return binding.root
    }




}