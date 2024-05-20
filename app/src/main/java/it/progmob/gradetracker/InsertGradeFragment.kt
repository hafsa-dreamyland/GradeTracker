package it.progmob.gradetracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import it.progmob.gradetracker.databinding.InsertGradeDataBinding

class InsertGradeFragment : Fragment() {

    //binding connected to the specific layout of the fragment
    private lateinit var binding: InsertGradeDataBinding

    // Use of viewModel among fragments to share data
    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout using data binding
        binding = InsertGradeDataBinding.inflate(inflater, container, false)
        val subjects = viewModel.getSubjectList()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, subjects)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.subject.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the NavController
        val navController = Navigation.findNavController(view)

        binding.buttonSubmit.setOnClickListener {
            val grade = binding.insertGrade.text.toString().toDoubleOrNull() ?: 0.0
            val credits = binding.insertCredits.text.toString().toDoubleOrNull() ?: 0.0
            val name = binding.subject.selectedItem.toString()

            viewModel.updateSubject(name, grade, credits)

            navController.navigate(R.id.action_insertGradeFragment_to_mainFragment)
        }
    }

}