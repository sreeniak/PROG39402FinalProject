package akshita.ken.nadin

import akshita.ken.nadin.databinding.FragmentAddMealBinding
import akshita.ken.nadin.databinding.FragmentHomeBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass.
 * Use the [addMealFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class addMealFragment : Fragment() {
    private var _binding: FragmentAddMealBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.btnHomeToWorkoutList.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_workoutListFragment)
//        }
    }
}