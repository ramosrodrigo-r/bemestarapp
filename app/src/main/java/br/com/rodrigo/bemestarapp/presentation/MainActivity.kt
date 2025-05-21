package br.com.rodrigo.bemestarapp.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.rodrigo.bemestarapp.data.local.AppDatabase
import br.com.rodrigo.bemestarapp.data.remote.RetrofitClient
import br.com.rodrigo.bemestarapp.domain.repository.CheckRepository
import br.com.rodrigo.bemestarapp.presentation.viewmodel.CheckViewModel
import br.com.rodrigo.bemestarapp.presentation.viewmodel.CheckViewModelFactory
import br.com.rodrigo.bemestarapp.domain.model.Check
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import br.com.rodrigo.bemestarapp.R
import br.com.rodrigo.bemestarapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var checkViewModel: CheckViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


        val dao = AppDatabase.getInstance(applicationContext).checkInDao()
        val api = RetrofitClient.api
        val repository = CheckRepository(dao, api)
        val factory = CheckViewModelFactory(this) // ✅ correto
        checkViewModel = ViewModelProvider(this, factory)[CheckViewModel::class.java]


        binding.fab.setOnClickListener { view ->
            val check = Check(
                date = "2025-05-21",
                mood = 2,
                note = "Hoje me senti bem concentrado.",
                motivation = 3,
                focus = 4,
                support = 3
            )

            checkViewModel.insertCheckIn(check)

            Snackbar.make(view, "Check-in salvo com sucesso!", Snackbar.LENGTH_SHORT)
                .setAnchorView(R.id.fab).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
