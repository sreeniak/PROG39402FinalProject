package akshita.ken.nadin

import akshita.ken.nadin.databinding.FragmentHomeBinding
import akshita.ken.nadin.databinding.FragmentWelcomeBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnHomeToWorkoutList.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_workoutListFragment)
        }

        binding.btnHomeToDietJournal.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_mealListFragment)
        }

        binding.btnHomeToExerciseSuggestions.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_exerciseSuggestionsFragment)
        }


    }
}