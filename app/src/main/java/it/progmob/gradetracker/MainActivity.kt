package it.progmob.gradetracker

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import it.progmob.gradetracker.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        // add the support for two way binding
        binding.lifecycleOwner = this

        // Set initial student data
        viewModel.studentName = "Marco Rossi"
        viewModel.studentID = "StudentID: S123456"

    }

    // Functions to Handle Menu Item with the NavController
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host)
        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }


}
