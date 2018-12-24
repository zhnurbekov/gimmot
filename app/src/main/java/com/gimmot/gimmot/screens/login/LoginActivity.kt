package com.gimmot.gimmot.screens.login

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.gimmot.gimmot.BaseActivity
import com.gimmot.gimmot.R
import com.gimmot.gimmot.screens.common.setupAuthGuard
import com.gimmot.gimmot.screens.common.setupBottomNavigation
import kotlinx.android.synthetic.main.activity_login.*
import android.content.Intent




class LoginActivity : BaseActivity() {

    lateinit var mViewModel: LoginViewModel


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
            mViewModel = initViewModel()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.frame_layout, DataAuthFragment())
                    .addToBackStack(null)
                    .commit()
        }
    }

     fun showProgressBar(show: Boolean) {
        if(show){
            progressbar.visibility = View.VISIBLE
        }else{
            progressbar.visibility = View.GONE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in supportFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }
}