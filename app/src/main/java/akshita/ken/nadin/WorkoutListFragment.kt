package akshita.ken.nadin

import akshita.ken.nadin.databinding.FragmentWorkoutListBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

class WorkoutListFragment : Fragment() {

    //viewbinding
    private var _binding: FragmentWorkoutListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: WorkoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflating layout
        _binding = FragmentWorkoutListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // binding.recycleView.adapter = WorkoutRecycleView()
        binding.recycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleView.setHasFixedSize(true)

        binding.addworkoutBtn.setOnClickListener {
            findNavController().navigate(R.id.action_workoutListFragment_to_addWorkoutFragment)
        }

    }
}