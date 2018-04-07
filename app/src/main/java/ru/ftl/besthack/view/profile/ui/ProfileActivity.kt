package ru.ftl.besthack.view.profile.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
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

    @SuppressLint("NewApi")
    override fun setUser(userModel: UserModel) {
        userName.text = if (userModel.name.isNotEmpty()) userModel.name else getString(R.string.profile_notfound_name)
        userMiddlename.text = if (userModel.middlename.isNotEmpty()) userModel.middlename else getString(R.string.profile_notfound_middle)
        userSurname.text = if (userModel.surname.isNotEmpty()) userModel.surname else getString(R.string.profile_notfound_surname)
        userAbout.text = when {
            userModel.about.isEmpty() -> getString(R.string.profile_notfound_about)
            android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N -> Html.fromHtml(getString(R.string.about, userModel.about), Html.FROM_HTML_MODE_LEGACY)
            else -> Html.fromHtml(getString(R.string.about, userModel.about))
        }
        userAbout.movementMethod = LinkMovementMethod.getInstance()

        userGroup.text = if (userModel.group.isNotEmpty()) getString(R.string.group, userModel.group) else {
            getString(R.string.profile_notfound_group)
        }
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
