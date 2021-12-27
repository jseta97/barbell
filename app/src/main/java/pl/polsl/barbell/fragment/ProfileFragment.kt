package pl.polsl.barbell.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pl.polsl.barbell.R
import pl.polsl.barbell.SignInActivity
import pl.polsl.barbell.databinding.FragmentProfileBinding
import pl.polsl.barbell.model.User
import pl.polsl.barbell.viewModel.ProfileViewModel

class ProfileFragment : Fragment() {

    protected var _binding: FragmentProfileBinding? = null
    protected val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by activityViewModels()

    private val firebaseAuth: FirebaseAuth by lazy { Firebase.auth }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        observeViewModel()
        profileViewModel.setUserDetails(firebaseAuth.currentUser?.uid.toString())
        profileViewModel.saveUser(
                User.Builder(
                        firebaseAuth.currentUser?.uid.toString(),
                        "name1",
                        firebaseAuth.currentUser?.email,
                        "url1").build())
        binding.logOutButton.setOnClickListener {
            profileViewModel.signOut()
            val intent = Intent(activity, SignInActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                findNavController().navigate(R.id.action_navigation_profile_to_settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observeViewModel() {
        profileViewModel.authenticatedUser.observeForever {
            it?.let { binding.user = it }
        }
    }
}