package ru.ftl.besthack.view.menu.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_users.*
import ru.ftl.besthack.R
import ru.ftl.besthack.data.auth.UserModel
import ru.ftl.besthack.utils.toast
import ru.ftl.besthack.view.menu.presenter.UserMenuPresenter

/**
 * @author Parpibaev Arthur <a.parpibaev97@gmail.com>
 * @project BestHack
 * @date 27.03.18
 */
class UserMenuFragment : MvpAppCompatFragment(), IUserMenuFragment {
    @InjectPresenter
    lateinit var presenter: UserMenuPresenter
    private var adapter = UserModelAdapter(emptyList())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list.layoutManager = LinearLayoutManager(this.context)
        list.adapter = adapter
        presenter.loadList()
    }

    fun setOnUserClickListener(listener: OnUserClickListener) {
        this.adapter.setOnUserClickListener(listener)
    }

    override fun setList(users: List<UserModel>) {
        adapter.setList(users)
    }

    override fun onError() {
        this.context!!.toast("Update error")
    }
}
