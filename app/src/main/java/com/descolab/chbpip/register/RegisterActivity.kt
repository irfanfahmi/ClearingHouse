package com.descolab.chbpip.register

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.descolab.chbpip.R
import com.descolab.chbpip.helper.DataConfig
import com.descolab.chbpip.helper.Utils
import com.descolab.chbpip.main.MainActivity
import com.descolab.chbpip.service.response.DataItem
import com.descolab.chbpip.service.response.DataUser
import id.zelory.compressor.Compressor
import kotlinx.android.synthetic.main.activity_register.*
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File
import java.io.FileInputStream

class RegisterActivity : AppCompatActivity(),RegisterContract.View {
    private var progressDialog : ProgressDialog? = null
    private var mActionListener: RegisterPresenter? = null
    private var filePicture1: File? = null
    private var lembaga = ArrayList<DataItem>()
    private var sublembaga = ArrayList<DataItem>()
    private var listLembaga = ArrayList<String>()
    private var listSubLembaga = ArrayList<String>()
    private var lembagaPosition = 0
    private var sublembagaPosition = 0
    private var idLembaga =  ""
    private var idSubLembaga =  ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        mActionListener = RegisterPresenter(this, this)
        mActionListener?.loadLembaga()
        btnSimpan.setOnClickListener {
            Log.d("filePicture1 ", filePicture1.toString())

            if (filePicture1 == null){

                val builder = androidx.appcompat.app.AlertDialog.Builder(this)
                builder?.setTitle(getString(R.string.app_name))
                builder?.setMessage("Foto wajib diisi!")
                builder?.setPositiveButton("Ya") { dialog, which ->
                    dialog.dismiss()
                }
//
//                builder?.setNegativeButton("Tidak") { dialog, which ->
//                    dialog.dismiss()
//                }
                val dialog: androidx.appcompat.app.AlertDialog? = builder?.create()
                dialog?.show()
            }else{
                mActionListener!!.saveregister(
                    etNama.text.toString(),
                    etNama.text.toString(),
                    etKementrian.text.toString(),
                    etLembaga.text.toString(),
                    etSatker.text.toString(),
                    "",
                    etNoHp.text.toString(),
                    etEmail.text.toString(),
                    etPassword.text.toString(),
                    idLembaga.toString(),
                    idSubLembaga.toString(),
                    filePicture1,
                )
            }

        }
        etKementrian.setOnClickListener {
            displayLembaga(it)
        }

        etLembaga.setOnClickListener {
            displaySubLembaga(it)
        }


        ivFoto.setOnClickListener {
            EasyImage.openChooserWithGallery(this,"Choose file",1)
        }
    }


    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }

    override fun nextStep(data: DataUser) {
        Log.d("nextStep ", data.name.toString())

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
        data.idInstansi?.let { DataConfig.setString(DataConfig.IDINSTANSI, it) }
        data.idLembaga?.let { DataConfig.setString(DataConfig.IDLEMBAGA, it) }

        DataConfig.setLogin()
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.app_name))
        builder.setMessage("Registrasi Berhasil")
        builder.setCancelable(false)
        builder.setPositiveButton("OK"){dialog, which ->
            dialog.dismiss()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()


    }

    override fun showLembaga(data: ArrayList<DataItem>) {
        Log.d("NDASH",  data.size.toString() )
        lembaga.clear()
        listLembaga.clear()
        for(div in data){
            lembaga.add(div)
            Log.d("NDASH Name",  div.namaLembaga.toString() )
            div.namaLembaga.toString()?.let { listLembaga.add(it) }
        }
    }

    override fun showSubLembaga(data: ArrayList<DataItem>) {
        listSubLembaga.clear()
        for(div in data){
            sublembaga.add(div)
            div.namaSubLembaga.toString()?.let { listSubLembaga.add(it) }
        }
        idSubLembaga = ""
        etLembaga.setText("")
    }

    fun displayLembaga(v: View){
        val array = arrayOfNulls<String>(listLembaga.size)
        listLembaga.toArray(array)
        Log.d("Lembaga", "Display = "+listLembaga.size )
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder?.setTitle("Instansi")
        builder?.setSingleChoiceItems( array, -1 ) {
                dialogInterface, i ->
            (v as EditText).setText(array[i])
            lembagaPosition = i
            Log.d("Instansi", "id = "+lembaga[lembagaPosition].id.toString() )
            Log.d("Instansi", "name = "+lembaga[lembagaPosition].namaLembaga.toString() )
            idLembaga = lembaga[lembagaPosition].id.toString()
            lembaga[lembagaPosition].id.toString()?.let { mActionListener?.loadSubLembaga(it) }

            dialogInterface.dismiss()
        }
        builder?.show()
    }

    fun displaySubLembaga(v: View){
        val array = arrayOfNulls<String>(listSubLembaga.size)
        listSubLembaga.toArray(array)
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder?.setTitle("Lembaga")
        builder?.setSingleChoiceItems( array, -1 ) {
                dialogInterface, i ->
            (v as EditText).setText(array[i])
            sublembagaPosition = i
            idSubLembaga = sublembaga[sublembagaPosition].id.toString()
            Log.d("Lembaga", "id = "+sublembaga[sublembagaPosition].id.toString() )
            Log.d("Lembaga", "name = "+sublembaga[sublembagaPosition].namaSubLembaga.toString() )

            dialogInterface.dismiss()
        }
        builder?.show()
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
                var compressedImageFile = Compressor(this@RegisterActivity)
                    .setMaxWidth(700)
                    .setMaxHeight(700)
                    .setQuality(90)
                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                    .compressToFile(imageFile)
                filePicture1 = compressedImageFile
                val options = BitmapFactory.Options()
                options.inPreferredConfig = Bitmap.Config.ARGB_8888
                val bitmap1 = BitmapFactory.decodeStream(FileInputStream(filePicture1), null, options)
                ivFoto.setImageBitmap(bitmap1)
            }

            override fun onCanceled(source: EasyImage.ImageSource?, type: Int) {
                showProgressDialog(false)
            }
        })
    }
}