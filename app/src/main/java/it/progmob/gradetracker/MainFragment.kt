package it.progmob.gradetracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import it.progmob.gradetracker.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    //binding connected to the specific layout of the fragment
    private lateinit var binding: MainFragmentBinding

    // Use of viewModel among fragments to share data
    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using data binding
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rv.layoutManager = LinearLayoutManager(requireContext())

        // Get the NavController
        val navController = Navigation.findNavController(view)

        viewModel.subjects.observe(viewLifecycleOwner, Observer { subjects ->
            // Update TextViews with student data
            binding.studentNameTextView.text = viewModel.studentName
            binding.studentIDTextView.text = viewModel.studentID

            // Calculate and update weighted average
            val weightedAverage = viewModel.calculateWeightedAverage()
            binding.weightedAverageTextView.text = "Weighted Average: ${String.format("%.2f", weightedAverage)}"

            // Update the RecyclerView
            val gradeAdapter = GradeAdapter(requireContext(), subjects)
            binding.rv.adapter = gradeAdapter
        })

        // Handle click events for grade change button
        binding.insertGradeButton.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_insertGradeFragment)
        }
    }
}