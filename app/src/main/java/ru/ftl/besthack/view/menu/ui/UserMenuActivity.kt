package ru.ftl.besthack.view.menu.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_users.*
import ru.ftl.besthack.R
import ru.ftl.besthack.data.auth.UserModel
import ru.ftl.besthack.utils.toast
import ru.ftl.besthack.view.menu.presenter.UserMenuPresenter


/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 27.03.18
 */

class UserMenuActivity : MvpAppCompatActivity(), IUserMenuActivity {
    @InjectPresenter
    lateinit var presenter: UserMenuPresenter
    private var adapter: UserModelAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        list.layoutManager = LinearLayoutManager(this)
        updateButton.setOnRefreshListener { presenter.loadList() }
    }

    override fun setList(users: List<UserModel>) {
        updateButton.isRefreshing = false
        if (adapter == null) {
            adapter = UserModelAdapter(users)
            list.adapter = adapter
            return
        }

        adapter!!.setList(users)
    }

    override fun onError() {
        toast("Update error")
    }
}