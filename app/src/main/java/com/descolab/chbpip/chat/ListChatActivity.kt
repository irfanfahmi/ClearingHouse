package com.descolab.chbpip.chat

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.descolab.chbpip.R
import com.descolab.chbpip.helper.Utils
import com.descolab.chbpip.main.MainActivity
import com.descolab.chbpip.service.response.DataItem
import kotlinx.android.synthetic.main.activity_list_chat.*
import kotlinx.android.synthetic.main.activity_list_chat.ivLabelMitra

class ListChatActivity : AppCompatActivity(),ChatContract.View, ListChatAdapter.ListDirektoratListener {
    private var progressDialog : ProgressDialog? = null
    private var mActionListener: ChatPresenter? = null
    private var mAdapter: ListChatAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_chat)
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        mActionListener = this.let { ChatPresenter(it, this) }
        mActionListener?.loadDirektorat()
        ivLabelMitra.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun showPercakapan() {
    }

    override fun showCekPercakapan(data: ArrayList<DataItem>) {
    }

    override fun showCekUpdatePercakapan(data: ArrayList<DataItem>) {

    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }

    override fun showListDirektorat(data: ArrayList<DataItem>) {
        if(data.isNotEmpty()){
            mAdapter = ListChatAdapter(this, data, this)
            rvListDirektorat?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rvListDirektorat?.setHasFixedSize(true)
            rvListDirektorat?.adapter = mAdapter
        }else{
            Utils.showToast(this, "Hasil pencarian tidak ditemukan")
        }
    }

    override fun toDetailDirektorat(item: DataItem) {
        val i = Intent(this, ChatActivity::class.java)
        i.putExtra("id",item.id.toString())
        i.putExtra("title",item.nama.toString())
        i.putExtra("tipePengajuan","2")
        startActivity(i)
    }
}