package com.gimmot.gimmot.screens.login

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.TextView
import com.gimmot.gimmot.R
import com.gimmot.gimmot.data.auth
import com.gimmot.gimmot.model.Country
import com.gimmot.gimmot.screens.nearby.NearbyActivity
import com.gimmot.gimmot.utils.showToast
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.fragment_authorization.*
import kotlinx.android.synthetic.main.dialog_country_list.view.*


class RegistrationFragment : Fragment() {
    private val TAG = RegistrationFragment::class.java!!.getSimpleName()
    lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var verificationId = ""
    lateinit var mCodeCountry: TextView
    lateinit var mCountry: TextView
    lateinit var mSmsCode: EditText
    lateinit var mPhoneNumber: EditText
    lateinit var countryPhoneCodeAdapter: CountryPhoneCodeAdapter
    lateinit var mLoginActivity: LoginActivity
    private lateinit var countryList: List<Country?>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_authorization, container, false)
    }


    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mLoginActivity = activity as LoginActivity
        mCodeCountry = country_code as TextView
        mCountry = country_name as TextView
        mSmsCode = sms_code as EditText
        mPhoneNumber = phone_number as EditText

        get_sms_code_btn.setOnClickListener {
            Log.e(TAG, "get_sms_code_btn_click")
            if (mCodeCountry.text.isNotEmpty() && mPhoneNumber.text.isNotEmpty()) {
                mLoginActivity.showProgressBar(true)
                verify()
            } else {
                activity?.showToast(getString(R.string.not_all_fields_filled))
            }
        }

       /* next_btn.setOnClickListener {
            if (mCodeCountry.text.isNotEmpty() && mPhoneNumber.text.isNotEmpty() && mSmsCode.text.isNotEmpty()) {
                signIn(PhoneAuthProvider.getCredential(verificationId, mSmsCode.text.toString()))
            } else {
                activity?.showToast(getString(R.string.not_all_fields_filled))
            }
        }
*/

        mLoginActivity.mViewModel.goToMainScreen.observe(this, Observer {
            activity?.showToast("Logged in Successfully :)")
            startActivity(Intent(activity, NearbyActivity::class.java))
        })

        mCountry.setOnClickListener {showCountryDialog()}
    }




    private fun signIn(credential: PhoneAuthCredential) {
        mLoginActivity.showProgressBar(true)
        mLoginActivity.mViewModel.onLoginClick(credential)
    }

    private fun verify() {
        verificationCallbacks()
        val phnNo = country_code.text.toString() + phone_number.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phnNo, 60, TimeUnit.SECONDS, activity!!, mCallbacks
        )
    }


    private fun verificationCallbacks() {
        Log.e(TAG, "verificationCallbacks")
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                mLoginActivity.showProgressBar(false)
                signIn(credential)
            }

            override fun onVerificationFailed(p0: FirebaseException?) {
                activity?.showToast("Введен неправильный формат данных")
                mLoginActivity.showProgressBar(false)
            }

            override fun onCodeSent(verfication: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
                super.onCodeSent(verfication, p1)
                verificationId = verfication.toString()
                mLoginActivity.showProgressBar(false)

            }

        }
    }

    fun showCountryDialog() {
        val builder = AlertDialog.Builder(activity!!)
        val dialogView = activity!!.layoutInflater.inflate(R.layout.dialog_country_list, null)
        builder.setView(dialogView)
        builder.setNegativeButton("отмена") { dialog, which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()

        mLoginActivity.mViewModel.country.observe(this, Observer {
            it?.let { countryList = it }
        countryPhoneCodeAdapter = CountryPhoneCodeAdapter(countryList as List<Country>, activity!!)
        dialogView.country_recycler_view.setLayoutManager(LinearLayoutManager(activity))
        dialogView.country_recycler_view.adapter = countryPhoneCodeAdapter
        countryPhoneCodeAdapter.getCodeListener = { country, code ->
            mCodeCountry.text = code
            mCountry.text = country
            dialog.dismiss()
        }
        })
    }

    fun isNetwork(): Boolean {
        val cs = Context.CONNECTIVITY_SERVICE
        val cm = activity?.getSystemService(cs) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null
    }
}