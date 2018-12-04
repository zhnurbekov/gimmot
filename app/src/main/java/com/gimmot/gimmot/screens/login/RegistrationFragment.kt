package com.gimmot.gimmot.screens.login

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.gimmot.gimmot.R
import com.gimmot.gimmot.model.CountryPhoneCode
import com.gimmot.gimmot.screens.nearby.NearbyActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.country_list_item.*
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.dialog_country_list.view.*


class RegistrationFragment : Fragment() {

    private val TAG = RegistrationFragment::class.java!!.getSimpleName()

    lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var mAuth: FirebaseAuth
    var verificationId = ""
    var countryList = ArrayList<CountryPhoneCode>()
    lateinit var mCodeCountry: TextView
    lateinit var mCountry: TextView
    lateinit var mSmsCode: EditText
    lateinit var mPhoneNumber: EditText
    lateinit var countryPhoneCodeAdapter: CountryPhoneCodeAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        mAuth = FirebaseAuth.getInstance()
        mCodeCountry = country_code as TextView
        mCountry = country_name as TextView
        mSmsCode = sms_code as EditText
        mPhoneNumber = phone_number as EditText
        val database = FirebaseDatabase.getInstance().reference

        get_sms_code_btn.setOnClickListener {
            Log.e(TAG, "get_sms_code_btn_click")
            if (valid()) verify()
        }

        database.child("counrty_phone_code").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach({
                    var item = it.getValue(CountryPhoneCode::class.java)
                    countryList.add(item!!)
                })

                mCountry.setOnClickListener {
                    val builder = AlertDialog.Builder(activity!!)
                    val dialogView = activity!!.layoutInflater.inflate(R.layout.dialog_country_list, null)
                    builder.setView(dialogView)
                    builder.setNegativeButton("отмена") { dialog, which ->
                        dialog.dismiss()
                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                    countryPhoneCodeAdapter = CountryPhoneCodeAdapter(countryList, activity!!)
                    dialogView.country_recycler_view.setLayoutManager(LinearLayoutManager(activity))
                    dialogView.country_recycler_view.adapter = countryPhoneCodeAdapter
                    countryPhoneCodeAdapter.getCodeListener = { country, code ->
                        mCodeCountry.text = code
                        mCountry.text = country
                        dialog.dismiss()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                print("")
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_phone_auth, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when (item.itemId) {
            R.id.action_next -> {
                if (valid()) {
                    authenticate()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        })
    }


    private fun verificationCallbacks() {
        Log.e(TAG, "verificationCallbacks")
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // progress.visibility = View.INVISIBLE
                signIn(credential)
            }

            override fun onVerificationFailed(p0: FirebaseException?) {
                print("")
                toast("Введен неправильный формат данных")

            }

            override fun onCodeSent(verfication: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
                super.onCodeSent(verfication, p1)
                verificationId = verfication.toString()
                //progress.visibility = View.INVISIBLE
            }

        }
    }


    private fun verify() {
        verificationCallbacks()
        val phnNo = country_code.text.toString() + phone_number.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phnNo,
                60,
                TimeUnit.SECONDS,
                activity!!,
                mCallbacks
        )
    }

    private fun signIn(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        toast("Logged in Successfully :)")
                        startActivity(Intent(activity, NearbyActivity::class.java))
                    } else {
                        print("")
                    }
                }
    }

    private fun authenticate() {
        signIn(PhoneAuthProvider.getCredential(verificationId, mSmsCode.text.toString()))
    }

    fun valid(): Boolean {
        if (mCodeCountry.text.isNotEmpty() && mPhoneNumber.text.isNotEmpty()) {
            return true
        } else {
            toast(getString(R.string.not_all_fields_filled))
            return false
        }
    }

    private fun toast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }

}