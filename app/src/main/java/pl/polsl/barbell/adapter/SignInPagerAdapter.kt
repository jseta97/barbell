package pl.polsl.barbell.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import pl.polsl.barbell.fragment.LoginFragment
import pl.polsl.barbell.fragment.RegisterFragment

/**
 * Sign in pager adapter
 *
 * @property itemsCount
 * @constructor
 *
 * @param fragmentActivity
 */
class SignInPagerAdapter(fragmentActivity: FragmentActivity, private val itemsCount: Int) :
        FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                LoginFragment()
            }
            1 -> {
                RegisterFragment()
            }
            else -> {
                throw IllegalArgumentException("Invalid position passed.")
            }
        }
    }
}