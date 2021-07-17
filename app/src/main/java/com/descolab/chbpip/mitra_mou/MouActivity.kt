package com.descolab.chbpip.mitra_mou

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.descolab.chbpip.R
import com.descolab.chbpip.helper.DataConfig
import com.descolab.chbpip.helper.Utils
import com.descolab.chbpip.service.response.DataItem
import kotlinx.android.synthetic.main.activity_mou.*

class MouActivity : AppCompatActivity(),MouContract.View,MouAdapter.MouListener {
    private var progressDialog : ProgressDialog? = null
    private var mActionListener: MouPresenter? = null
    private var mAdapter: MouAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mou)
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        mActionListener = MouPresenter(this, this)
        ivLabelMitra.setOnClickListener {
            onBackPressed()
        }

        val code :String? = intent.getStringExtra("code")
        if (code.toString().equals("1")){
            //todo File berdasarkan ID User
//            mActionListener?.loadMou()
            mActionListener?.loadMou(
                DataConfig.getString(DataConfig.USER_ID),
                DataConfig.getString(DataConfig.IDINSTANSI),
                DataConfig.getString(DataConfig.IDLEMBAGA)
            )
        }else if(code.toString().equals("2")){
            mActionListener?.loadPks(DataConfig.getString(
                DataConfig.USER_ID),
                DataConfig.getString(DataConfig.IDINSTANSI),
                DataConfig.getString(DataConfig.IDLEMBAGA)
            )
        }else{
            mActionListener?.loadMonev(DataConfig.getString(
                DataConfig.USER_ID),
                DataConfig.getString(DataConfig.IDINSTANSI),
                DataConfig.getString(DataConfig.IDLEMBAGA)
            )

        }

    }

    override fun showMou(data: ArrayList<DataItem>) {
        if(data.isNotEmpty()){
            mAdapter = MouAdapter(this, data, this)
            rvMou?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rvMou?.setHasFixedSize(true)
            rvMou?.adapter = mAdapter
        }else{
            Utils.showToast(this, "Data Tidak Ditemukan")
        }
    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }

    override fun toDetailMou(item: DataItem) {
        val i = Intent(this, PdfViewActivity::class.java)
        i.putExtra("id",item.id.toString())
        i.putExtra("title",item.namaFile.toString())
        i.putExtra("file",item.file.toString())
        startActivity(i)
    }
}