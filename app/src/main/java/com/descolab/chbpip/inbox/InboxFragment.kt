package com.descolab.chbpip.inbox

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.descolab.chbpip.R
import com.descolab.chbpip.chat.ChatActivity
import com.descolab.chbpip.chat.ListChatActivity
import com.descolab.chbpip.helper.DataConfig
import com.descolab.chbpip.service.response.DataItem
import kotlinx.android.synthetic.main.fragment_inbox.*
import kotlinx.android.synthetic.main.fragment_inbox.ivLabelMitra
import kotlinx.android.synthetic.main.fragment_profile.*


class InboxFragment : Fragment(),InboxContract.View{
    private var progressDialog : ProgressDialog? = null
    private var mActionListener: InboxPresenter? = null
    private var mAdapter: InboxAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inbox, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(context)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        mActionListener = context?.let { InboxPresenter(it, this) }
        mActionListener?.loadInbox(DataConfig.getString(DataConfig.USER_ID))

        
        if(DataConfig.getString(DataConfig.ROLE_ID).equals("1")){
            ivLabelMitra.setBackgroundResource(R.drawable.ic_label_non_mitra)
            btnTanyaDella.visibility = View.GONE
        }else{
            ivLabelMitra.setBackgroundResource(R.drawable.ic_label_mitra)
            btnTanyaDella.visibility = View.VISIBLE
        }

        btnTanyaDella.setOnClickListener {
            val i = Intent(getActivity(), ListChatActivity::class.java)
            context!!.startActivity(i)
        }
        ivLabelMitra.setOnClickListener {
            activity?.onBackPressed()
        }

    }

    override fun showInbox(data: ArrayList<DataItem>) {
        if (data.isNullOrEmpty()){
            tvNoData.visibility = View.VISIBLE
        }else{
            tvNoData.visibility = View.GONE

            mAdapter = context?.let {
            InboxAdapter(it, data)
        }
        rvListNotifikasi?.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        rvListNotifikasi?.setHasFixedSize(true)
        rvListNotifikasi?.adapter = mAdapter
        }

    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }


}