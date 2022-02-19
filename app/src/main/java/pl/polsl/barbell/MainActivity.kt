package pl.polsl.barbell

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pl.polsl.barbell.viewModel.ProfileViewModel

/**
 * Main activity
 *
 * @constructor Create empty Main activity
 */
class MainActivity : AppCompatActivity() {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        checkUser(navController)
        val appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.navigation_profile,
                        R.id.navigation_history,
                        R.id.navigation_workout,
                        R.id.navigation_exercises,
                        R.id.navigation_measure,
                        R.id.settingsFragment,
                        R.id.exerciseDetailsFragment
                )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun checkUser(navController: NavController) {
        viewModel.setUser(Firebase.auth.currentUser?.uid.toString()) {
            when {
                Firebase.auth.currentUser == null -> {
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                }
            }

        }
    }
}
