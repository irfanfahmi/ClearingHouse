package com.descolab.chbpip.chat

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.descolab.chbpip.R
import com.descolab.chbpip.helper.DataConfig
import com.descolab.chbpip.service.response.DataItem
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity(),ChatContract.View {
    private var mAdapter: ChatPercakapanAdapter? = null
    private var progressDialog : ProgressDialog? = null
    private var mActionListener: ChatPresenter? = null
    private var idDirektorat : String? = ""
    private var jmlChat : Int? = 0
    val handler = Handler()
    lateinit var refresh: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        mActionListener = this.let { ChatPresenter(it, this) }
        idDirektorat  = intent.getStringExtra("id").toString()
        val titleDirektorat :String? = intent.getStringExtra("title")
        val tipePengajuan :String? = intent.getStringExtra("tipePengajuan")


//        if(jmlChat>data)
        mActionListener?.cekPercakapan(DataConfig.getString(DataConfig.USER_ID),idDirektorat.toString())

//        refresh = Runnable { // Do something
//            //cek update jumlah chat
//
//            mActionListener?.cekUpdatePercakapan(DataConfig.getString(DataConfig.USER_ID),idDirektorat.toString())
//            handler.postDelayed(refresh, 3000)
//        }
//        handler.post(refresh)

        btn_refresh.setOnClickListener {
            mActionListener?.cekPercakapan(DataConfig.getString(DataConfig.USER_ID),idDirektorat.toString())
        }
        sr_percakapan.setOnRefreshListener {
            mActionListener?.cekPercakapan(DataConfig.getString(DataConfig.USER_ID),idDirektorat.toString())
            sr_percakapan.isRefreshing = false
        }

        send_follow.setOnClickListener {
            mActionListener?.postPercakapan(DataConfig.getString(DataConfig.USER_ID),
                edittext_follow.getText().toString(),idDirektorat.toString(),
            tipePengajuan.toString())
        }
        ivLabelMitra.setOnClickListener {
            onBackPressed()
        }

    }

    override fun showPercakapan() {
        Log.d("Cek","Sukses Post")
        mActionListener?.cekPercakapan(DataConfig.getString(DataConfig.USER_ID),idDirektorat.toString())
        edittext_follow.setText("")
    }

    override fun showCekPercakapan(data: ArrayList<DataItem>) {
        mAdapter = ChatPercakapanAdapter(this, data)
        rvPercakapan.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvPercakapan.setHasFixedSize(true)
        rvPercakapan.adapter = mAdapter
        Log.d("ChatActivity","Cek Isi "+data.size.toString())
        jmlChat = data.size
        edittext_follow.setText("")
    }

    override fun showCekUpdatePercakapan(data: ArrayList<DataItem>) {

    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }

    override fun showListDirektorat(data: ArrayList<DataItem>) {
    }
}