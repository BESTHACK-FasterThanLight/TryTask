package ru.ftl.besthack.view.menu.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_users.*
import ru.ftl.besthack.R
import ru.ftl.besthack.data.auth.UserModel
import ru.ftl.besthack.utils.toast
import ru.ftl.besthack.view.menu.presenter.UserMenuPresenter
import ru.ftl.besthack.view.profile.ui.ProfileActivity

/**
 * @author Parpibaev Arthur <a.parpibaev97@gmail.com>
 * @project BestHack
 * @date 27.03.18
 */
class UserMenuActivity : MvpAppCompatActivity(), IUserMenuView {
    @InjectPresenter
    lateinit var presenter: UserMenuPresenter
    private var adapter = UserModelAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        presenter.loadList()

        adapter.setOnUserClickListener({ openDescription(it) })
    }

    override fun setList(users: List<UserModel>) {
        adapter.setList(users)
    }

    override fun onError() {
        this.toast("Update error")
    }

    private fun openDescription(userModel: UserModel) {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra(UserModel.EXTRA_NAME, userModel)
        startActivity(intent)
    }
}
