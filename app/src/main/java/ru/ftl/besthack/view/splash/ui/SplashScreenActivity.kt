package ru.ftl.besthack.view.splash.ui

import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.ftl.besthack.R
import ru.ftl.besthack.view.menu.ui.UserMenuActivity
import ru.ftl.besthack.view.splash.presenter.SplashPresenter

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 07.04.18
 */

class SplashScreenActivity : MvpAppCompatActivity(), ISplashView {
    @InjectPresenter
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_splash)

        presenter.loadUsers()
    }

    override fun finishLoad() {
        val intent = Intent(this, UserMenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}