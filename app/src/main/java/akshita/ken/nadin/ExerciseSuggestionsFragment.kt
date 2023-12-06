package akshita.ken.nadin

import akshita.ken.nadin.databinding.FragmentExerciseSuggestionsBinding
import akshita.ken.nadin.databinding.FragmentWelcomeBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.navigation.fragment.findNavController

class ExerciseSuggestionsFragment : Fragment() {
    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exercise_suggestions, container, false)

        // Initialize WebView
        webView = view.findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()

        // Set click listeners for buttons
        view.findViewById<Button>(R.id.upperBodyButton).setOnClickListener {
            loadUrl("https://www.muscleandstrength.com/workouts/the-best-upper-body-workout-routine")
        }

        view.findViewById<Button>(R.id.lowerBodyButton).setOnClickListener {
            loadUrl("https://www.beachbodyondemand.com/blog/lower-body-workout-exercises")
        }

        view.findViewById<Button>(R.id.hiitButton).setOnClickListener {
            loadUrl("https://www.menshealth.com/fitness/a25424850/best-hiit-exercises-workout/")
        }

        view.findViewById<Button>(R.id.cardioButton).setOnClickListener {
            loadUrl("https://www.medicalnewstoday.com/articles/cardio-exercises-at-home")
        }

        return view
    }

    private fun loadUrl(url: String) {
        webView.loadUrl(url)
    }

}
//    private var _binding: FragmentExerciseSuggestionsBinding? = null
//    private val binding get() = _binding!!
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        _binding = FragmentExerciseSuggestionsBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
//        super.onViewCreated(view, savedInstanceState)
//
////        binding.btnSuggestionsToWorkoutList.setOnClickListener {
////            findNavController().navigate(R.id.)
////        }
//    }
//
//}