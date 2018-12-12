package com.gimmot.gimmot.screens.common

import android.Manifest.permission.CAMERA
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import com.gimmot.gimmot.screens.login.LoginActivity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CameraHelper(private val activity: LoginActivity) {
    var imageUri: Uri? = null
    val REQUEST_CODE = 1
     val GALLERY = 2
    private val simpleDateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)

    fun takeCameraPicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(activity.packageManager) != null) {
            val imageFile = createImageFile()
            imageUri = FileProvider.getUriForFile(activity,
                    "com.gimmot.gimmot.fileprovider",
                    imageFile)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            activity.startActivityForResult(intent, REQUEST_CODE)
        }
       /* val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        activity.startActivityForResult(intent, REQUEST_CODE)*/
    }

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activity.startActivityForResult(galleryIntent, GALLERY)
    }

    private fun createImageFile(): File {
        val storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
                "JPEG_${simpleDateFormat.format(Date())}_",
                ".jpg",
                storageDir
        )
    }

}