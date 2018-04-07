package ru.ftl.besthack.view.krop.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.mlsdev.rximagepicker.RxImageConverters
import com.mlsdev.rximagepicker.RxImagePicker
import com.mlsdev.rximagepicker.Sources
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_krop.*
import ru.ftl.besthack.R
import ru.ftl.besthack.data.auth.UserModel
import ru.ftl.besthack.view.krop.presenter.KropPresenter
import timber.log.Timber

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.04.18
 */

class KropActivity : MvpAppCompatActivity(), IKropView {
    val REQUEST_IMAGE_CODE = 10
    lateinit var rxPermissions: RxPermissions
    @InjectPresenter
    lateinit var presenter: KropPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_krop)

        rxPermissions = RxPermissions(this)
        presenter.loadImage()

        val userModel = intent.getParcelableExtra<UserModel>(UserModel.EXTRA_NAME)
        presenter.storeUser(userModel)
    }

    override fun requestAndOpenPicker() {
        rxPermissions
                .request(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .subscribe {
                    if (it) {
                        openPicker()
                    } else {
                        requestAndOpenPicker()
                    }
                }
    }

    private fun openPicker() {
        RxImagePicker.with(this)
                .requestImage(Sources.GALLERY)
                .flatMap { RxImageConverters.uriToBitmap(baseContext, it) }
                .subscribe({ presenter.onImageLoad(it) }, Timber::e)
    }

    override fun setBitmap(bitmap: Bitmap) {
        krop_view.setBitmap(bitmap)
    }

    override fun showLoading(isVisible: Boolean) {
        progress_bar.visibility = if (isVisible) View.VISIBLE else View.GONE
        krop_view.visibility = if (isVisible) View.GONE else View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.submit_krop, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.done_item) {
            presenter.submit(krop_view.getCroppedBitmap())
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun finishForResult() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}