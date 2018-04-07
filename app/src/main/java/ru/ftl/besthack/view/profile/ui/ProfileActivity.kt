package ru.ftl.besthack.view.profile.ui

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_profile.*
import ru.ftl.besthack.R
import ru.ftl.besthack.data.auth.UserModel
import ru.ftl.besthack.di.GlideApp
import ru.ftl.besthack.view.profile.presenter.ProfilePresenter
import java.io.File

/**
 * Created by john on 05.04.18.
 */

class ProfileActivity : MvpAppCompatActivity(), IProfileView {

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val userModel = intent.getParcelableExtra<UserModel>(UserModel.EXTRA_NAME)
        presenter.loadUser(userModel)
    }

    override fun setUser(userModel: UserModel) {
        userName.text = userModel.name
        userMiddlename.text = userModel.middlename
        userSurname.text = userModel.surname
        userAbout.text = getString(R.string.about, userModel.about)
        userGroup.text = getString(R.string.group, userModel.group)
        if (userModel.imageUrl.isNotEmpty()) {
            GlideApp.with(this)
                    .load(File(userModel.imageUrl))
                    .circleCrop()
                    .placeholder(R.drawable.ic_action_name)
                    .error(R.drawable.ic_action_name)
                    .into(userImage)
        } else {
            userImage.setImageResource(R.drawable.ic_action_name)
        }
    }
}
