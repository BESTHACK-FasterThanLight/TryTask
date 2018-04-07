package ru.ftl.besthack.view.profile_add.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_user.*
import ru.ftl.besthack.R
import ru.ftl.besthack.data.auth.UserModel
import ru.ftl.besthack.di.GlideApp
import ru.ftl.besthack.utils.toast
import ru.ftl.besthack.view.krop.ui.KropActivity
import ru.ftl.besthack.view.profile_add.presenter.AddUserPresenter
import java.io.File

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 07.04.18
 */
class AddUserActivity : MvpAppCompatActivity(), IAddUserView {
    @InjectPresenter
    lateinit var presenter: AddUserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        presenter.loadDraft()
        userAvatar.setOnClickListener { openPicker() }
        fab.setOnClickListener { presenter.submit(getUser()) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.loadDraft()
    }

    override fun setUser(userModel: UserModel) {
        userName.setText(userModel.name)
        userSurname.setText(userModel.surname)
        userMiddlename.setText(userModel.middlename)
        userAbout.setText(userModel.about)
        userGroup.setText(userModel.group)

        if (userModel.imageUrl.isNotEmpty()) {
            GlideApp.with(this)
                    .load(File(userModel.imageUrl))
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .circleCrop()
                    .placeholder(R.drawable.ic_action_name)
                    .error(R.drawable.ic_action_name)
                    .into(userAvatar)
        } else {
            userAvatar.setImageResource(R.drawable.ic_action_name)
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.saveDraft(getUser())
    }

    private fun getUser(): UserModel {
        val userModel = UserModel()
        userModel.name = userName.text.toString()
        userModel.surname = userSurname.text.toString()
        userModel.middlename = userMiddlename.text.toString()
        userModel.about = userAbout.text.toString()
        userModel.group = userGroup.text.toString()
        return userModel
    }

    private fun openPicker() {
        val intent = Intent(this, KropActivity::class.java)
        intent.putExtra(UserModel.TABLE_NAME, getUser())
        startActivityForResult(intent, 1)
    }

    override fun onLoading(visible: Boolean) {
        progress_bar.visibility = if (visible) View.VISIBLE else View.GONE
        fab.visibility = if (visible) View.GONE else View.VISIBLE
    }

    override fun onError(resId: Int) {
        toast(resId)
    }

    override fun finishForResult() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}