package ru.ftl.besthack.view.menu.ui

import android.content.res.Configuration
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import ru.ftl.besthack.R
import ru.ftl.besthack.data.auth.UserModel
import ru.ftl.besthack.view.menu.UserProfile.ProfileFragment


/**
 * @author Nikita Kulikov <nikita@kulikof.ru> Parpibaev Arthur <a.parpibaev97@gmail.com>
 * @project BestHack
 * @date 27.03.18
 */

class UserMenuActivity : MvpAppCompatActivity() {

    private val USERMODEL = "user_model"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        createList()
        if (savedInstanceState?.getSerializable(USERMODEL) != null) {
            openDescription(savedInstanceState.getParcelable(USERMODEL) as UserModel)
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val manager = supportFragmentManager
        var  profileFragment:  ProfileFragment? = null

        if (resources.configuration.orientation != Configuration.ORIENTATION_LANDSCAPE) {
             profileFragment = manager.findFragmentById(R.id.container_right) as  ProfileFragment
        } else {
            val fragment = manager.findFragmentById(R.id.container)
            if (fragment is  ProfileFragment) {
                 profileFragment = fragment
            }
        }
        if ( profileFragment != null) {
            outState.putParcelable(USERMODEL,  profileFragment.user)
        }
    }

    private fun createList() {

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        manager.popBackStack()

        val userMenuFragment = UserMenuFragment()
        userMenuFragment.setOnUserClickListener(object : OnUserClickListener {
            override fun onUserClick(userModel: UserModel) {
                openDescription(userModel)
            }
        })

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            transaction.replace(R.id.container_left, userMenuFragment)
        } else {
            transaction.replace(R.id.container, userMenuFragment)
        }

        transaction.commit()
    }

    private fun openDescription(userModel : UserModel) {

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val  profileFragment =  ProfileFragment()

        val bundle = Bundle()
        bundle.putParcelable(USERMODEL, userModel)
        profileFragment.arguments = bundle

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            transaction.replace(R.id.container_right,  profileFragment)
        } else {
            transaction.replace(R.id.container,  profileFragment)
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }
}