package com.gimmot.gimmot.screens.login

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.auth.data.model.User
import com.gimmot.gimmot.R
import com.gimmot.gimmot.data.uid
import kotlinx.android.synthetic.main.fragment_data_auth.*
import java.text.SimpleDateFormat
import java.util.*

class DataAuthFragment : Fragment() {

    private val TAG = DataAuthFragment::class.java!!.getSimpleName()
    lateinit var mLoginActivity: LoginActivity


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_data_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mLoginActivity = activity as LoginActivity

        var cal = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
            borth_date.setText(sdf.format(cal.time).toString())
        }

        borth_date.setOnClickListener {
            DatePickerDialog(activity, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        next_btn.setOnClickListener {
            var user = com.gimmot.gimmot.model.User(name = name.text.toString(),birth_date = cal.time)
            mLoginActivity.mViewModel.creatUser(user, uid!!)
        }
    }

    fun initCalendar(){

    }
}