package com.gimmot.gimmot.screens.login

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gimmot.gimmot.R
import com.gimmot.gimmot.data.uid
import com.gimmot.gimmot.model.User
import com.gimmot.gimmot.screens.common.CameraHelper
import com.gimmot.gimmot.utils.rotateBitmap
import kotlinx.android.synthetic.main.fragment_data_auth.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class DataAuthFragment : Fragment() {
    private val TAG = DataAuthFragment::class.java!!.getSimpleName()
    lateinit var mLoginActivity: LoginActivity
    private lateinit var mCamera: CameraHelper
    private lateinit var mUser: User


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_data_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mLoginActivity = activity as LoginActivity
        mCamera = CameraHelper(mLoginActivity)
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

        logo.setOnClickListener { getImages() }
        next_btn.setOnClickListener {
            var user = com.gimmot.gimmot.model.User(name = name.text.toString(), birth_date = cal.time)
            mLoginActivity.mViewModel.creatUser(user, uid!!)
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == mCamera.REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            val bitmap = MediaStore.Images.Media.getBitmap(mLoginActivity.contentResolver, mCamera.imageUri)
            //logo.setImageBitmap( rotateBitmap(bitmap,-90f))
           /* UCrop.of(mCamera.imageUri!!, Uri.fromFile(File(mLoginActivity.cacheDir,"imageCrop.jpg")))
                    .withAspectRatio(16F, 9F)
                    .withMaxResultSize(450, 450)
                    .start(mLoginActivity);*/
        }
        if (requestCode == mCamera.GALLERY && data != null) {
            val bitmap = MediaStore.Images.Media.getBitmap(mLoginActivity.contentResolver, data.data)
            logo.setImageBitmap(bitmap)
        }
    }


    fun getImages() {
        val pictureDialog = AlertDialog.Builder(mLoginActivity)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Camera", "galery")
        pictureDialog.setItems(pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> mCamera.takeCameraPicture()
                1 -> mCamera.choosePhotoFromGallary()
            }
        }
        pictureDialog.show()
    }
}