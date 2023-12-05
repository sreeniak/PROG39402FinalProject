package akshita.ken.nadin

import akshita.ken.nadin.databinding.FragmentExerciseSuggestionsBinding
import akshita.ken.nadin.databinding.FragmentWelcomeBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

class ExerciseSuggestionsFragment : Fragment() {
    private var _binding: FragmentExerciseSuggestionsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentExerciseSuggestionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

//        binding.btnSuggestionsToWorkoutList.setOnClickListener {
//            findNavController().navigate(R.id.)
//        }
    }

}