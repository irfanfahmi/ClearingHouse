package com.descolab.chbpip.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.descolab.chbpip.R
import com.descolab.chbpip.edit_profile.EditProfilActivity
import com.descolab.chbpip.helper.DataConfig
import com.descolab.chbpip.helper.Tools
import com.descolab.chbpip.helper.Utils
import com.descolab.chbpip.login.PraLoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(DataConfig.getString(DataConfig.ROLE_ID).equals("1")){
            ivLabelMitra.setBackgroundResource(R.drawable.ic_label_non_mitra)
        }else{
            ivLabelMitra.setBackgroundResource(R.drawable.ic_label_mitra)
        }
        etNama.text = DataConfig.getString(DataConfig.NAME)
        etLembaga.text = DataConfig.getString(DataConfig.INSTANSI)
        etSatker.text = DataConfig.getString(DataConfig.SATKER)
        etNoId.text = DataConfig.getString(DataConfig.NO_ID)
        etNoHp.text = DataConfig.getString(DataConfig.PHONE_NUMBER)
        etEmail.text = DataConfig.getString(DataConfig.EMAIL)

        btnEdit.setOnClickListener {
            val i = Intent(activity, EditProfilActivity::class.java)
            startActivity(i)
        }

        ivLabelMitra.setOnClickListener {
            activity?.onBackPressed()
        }

        val baseURL: String = context!!.getString(R.string.base_url)
        context?.let {
            Tools.displayImageOriginal(
                it,
                imageView9,
                baseURL + DataConfig.getString(DataConfig.AVATAR)
            )
            btnLogout.setOnClickListener {

                val builder = activity?.let { it1 -> AlertDialog.Builder(it1) }
                builder?.setTitle(getString(R.string.app_name))
                builder?.setMessage("Apakah anda ingin logout?")
                builder?.setPositiveButton("Ya") { dialog, which ->
                    DataConfig.clearAll()
                    val i = Intent(activity, PraLoginActivity::class.java)
                    startActivity(i)
                    activity?.finish()
                    context?.let { it1 -> Utils.showToast(it1, "Logout Berhasil") }
                    dialog.dismiss()
                }

                builder?.setNegativeButton("Tidak") { dialog, which ->
                    dialog.dismiss()
                }
                val dialog: AlertDialog? = builder?.create()
                dialog?.show()

            }
        }

    }
}