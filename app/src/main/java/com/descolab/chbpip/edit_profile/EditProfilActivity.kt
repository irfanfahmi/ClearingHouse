package com.descolab.chbpip.edit_profile

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import com.descolab.chbpip.R
import com.descolab.chbpip.helper.DataConfig
import com.descolab.chbpip.helper.Tools
import com.descolab.chbpip.helper.Utils
import com.descolab.chbpip.main.MainActivity
import com.descolab.chbpip.service.response.DataUser
import id.zelory.compressor.Compressor
import kotlinx.android.synthetic.main.activity_edit_profil.*
import kotlinx.android.synthetic.main.activity_edit_profil.btnEdit
import kotlinx.android.synthetic.main.activity_edit_profil.btnSimpan
import kotlinx.android.synthetic.main.activity_edit_profil.etEmail
import kotlinx.android.synthetic.main.activity_edit_profil.etLembaga
import kotlinx.android.synthetic.main.activity_edit_profil.etNama
import kotlinx.android.synthetic.main.activity_edit_profil.etNoHp
import kotlinx.android.synthetic.main.activity_edit_profil.etNoId
import kotlinx.android.synthetic.main.activity_edit_profil.etPassword
import kotlinx.android.synthetic.main.activity_edit_profil.etSatker
import kotlinx.android.synthetic.main.activity_edit_profil.imageView9
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File
import java.io.FileInputStream

class EditProfilActivity : AppCompatActivity(),EditProfilContract.View {
    private var progressDialog : ProgressDialog? = null
    private var mActionListener: EditProfilPresenter? = null
    private var filePicture1: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        mActionListener = EditProfilPresenter(this, this)

        etNama.setText((DataConfig.getString(DataConfig.NAME)))
        etLembaga.setText((DataConfig.getString(DataConfig.INSTANSI)))
        etSatker.setText((DataConfig.getString(DataConfig.SATKER)))
        etNoId.setText((DataConfig.getString(DataConfig.NO_ID)))
        etNoHp.setText((DataConfig.getString(DataConfig.PHONE_NUMBER)))
        etEmail.setText((DataConfig.getString(DataConfig.EMAIL)))
        etPassword.setText((DataConfig.getString(DataConfig.PASSWORD)))
        val baseURL: String = getString(R.string.base_url)
        Tools.displayImageOriginal(
                this,
                imageView9,
                baseURL + DataConfig.getString(DataConfig.AVATAR)
            )

        ivLabelMitra.setOnClickListener {
            onBackPressed()
        }



        btnSimpan.setOnClickListener {
            mActionListener!!.saveUpdate(
                (DataConfig.getString(DataConfig.USER_ID)),
                etNama.text.toString(),
                etNama.text.toString(),
                etLembaga.text.toString(),
                etLembaga.text.toString(),
                etSatker.text.toString(),
                etNoId.text.toString(),
                etNoHp.text.toString(),
                etEmail.text.toString(),
                etPassword.text.toString(),
                filePicture1,
            )
        }

        btnEdit.setOnClickListener {
            EasyImage.openChooserWithGallery(this,"Choose file",1)

        }
    }

    private fun updatePasswordVisibility(editText: AppCompatEditText) {
        if (editText.transformationMethod is PasswordTransformationMethod) {
            editText.transformationMethod = null
        } else {
            editText.transformationMethod = PasswordTransformationMethod()
        }
        editText.setSelection(editText.length())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, object : DefaultCallback() {
            override fun onImagePickerError(e: Exception?, source: EasyImage.ImageSource?, type: Int) {
                //Some error handling
                showProgressDialog(false)
            }

            override fun onImagePicked(imageFile: File, source: EasyImage.ImageSource, type: Int) {

                Log.d("Test", "imageFile " +imageFile.length())
                val fileSizeInKB = imageFile.length() / 1024
                // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
                Log.d("Test", "fileSizeInKB " +fileSizeInKB.toString())
                val fileSizeInMB = fileSizeInKB / 1024
                Utils.applyRotationIfNeeded(imageFile)
                var compressedImageFile = Compressor(this@EditProfilActivity)
                    .setMaxWidth(700)
                    .setMaxHeight(700)
                    .setQuality(90)
                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                    .compressToFile(imageFile)
                filePicture1 = compressedImageFile
                val options = BitmapFactory.Options()
                options.inPreferredConfig = Bitmap.Config.ARGB_8888
                val bitmap1 = BitmapFactory.decodeStream(FileInputStream(filePicture1), null, options)
                imageView9.setImageBitmap(bitmap1)
            }

            override fun onCanceled(source: EasyImage.ImageSource?, type: Int) {
                showProgressDialog(false)
            }
        })
    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }

    override fun nextStep(data: DataUser) {
        data.id?.let { DataConfig.setString(DataConfig.USER_ID, it) }
        data.name?.let { DataConfig.setString(DataConfig.NAME, it) }
        data.alias?.let { DataConfig.setString(DataConfig.ALIAS, it) }
        data.instansi?.let { DataConfig.setString(DataConfig.INSTANSI, it) }
        data.unitSatker?.let { DataConfig.setString(DataConfig.SATKER, it) }
        data.noId?.let { DataConfig.setString(DataConfig.NO_ID, it) }
        data.noHp?.let { DataConfig.setString(DataConfig.PHONE_NUMBER, it) }
        data.email?.let { DataConfig.setString(DataConfig.EMAIL, it) }
        data.password?.let { DataConfig.setString(DataConfig.PASSWORD, it) }
        data.foto?.let { DataConfig.setString(DataConfig.AVATAR, it) }
        data.roleUser?.let { DataConfig.setString(DataConfig.ROLE_ID, it) }

        DataConfig.setLogin()
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.app_name))
        builder.setMessage("Update Berhasil")
        builder.setCancelable(false)
        builder.setPositiveButton("OK"){dialog, which ->
            dialog.dismiss()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}