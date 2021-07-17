package com.descolab.chbpip.inbox

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.descolab.chbpip.R
import com.descolab.chbpip.chat.ChatActivity
import com.descolab.chbpip.service.response.DataItem
import kotlinx.android.synthetic.main.item_inbox.view.*

class InboxAdapter(private val mContext: Context,
                   val mItems: ArrayList<DataItem>) :
    RecyclerView.Adapter<InboxAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater

    init {
        mInflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InboxAdapter.ViewHolder {
        val view = mInflater.inflate(R.layout.item_inbox, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return mItems.size
        //return 5
    }

    override fun onBindViewHolder(holder: InboxAdapter.ViewHolder, position: Int) {
        if (0 <= position && position < mItems.size) {
            val data = mItems[position]
            // Bind your data here
            holder.bind(data)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: DataItem) {
                itemView.tvHari.text = data.perihal.toString()
                itemView.tvTanggal.text = data.tanggal.toString()

            itemView.btnBales.setOnClickListener {
                val i = Intent(mContext, ChatActivity::class.java)
                i.putExtra("id",data.idDirektorat)
                i.putExtra("title",data.namaDirektorat.toString())
                i.putExtra("tipePengajuan",data.tipePengajuan.toString())
                mContext.startActivity(i)
            }

            Log.d("Inbox : ","Tipe Pengajuan = "+data.tipePengajuan.toString())
            Log.d("Inbox : ","Tipe kerjasama = "+data.kerjasama.toString())
            if(data.tipePengajuan.toString().equals("1")){

                itemView.tvTitle.text = "Usulan Kerjasama Sebagai Berikut"
                itemView.textView8.visibility = View.GONE
                itemView.textWiew7.visibility = View.GONE
                itemView.tvJam.visibility = View.GONE
                itemView.textView6.text = "Direktorat"
                itemView.tvDirektorat.text = data.namaDirektorat.toString()
                itemView.tvStatus.text = "Status : "+data.namaStatus.toString()

                //label pesan
                itemView.textView13.visibility = View.GONE
                itemView.textview12.visibility = View.GONE
                itemView.tvPesan.visibility = View.GONE
                itemView.btnBales.visibility = View.GONE

            }else if (data.tipePengajuan.toString().equals("2")){
                itemView.tvTitle.text = "Usulan Pertemuan Sebagai Berikut"
                itemView.textView8.visibility = View.VISIBLE
                itemView.textWiew7.visibility = View.VISIBLE
                itemView.tvJam.visibility = View.VISIBLE
                itemView.tvJam.text = data.jam.toString()
                itemView.textView6.text = "Tema"
                itemView.tvDirektorat.text = data.tema.toString()
                itemView.tvStatus.visibility = View.GONE
                itemView.imageView7.visibility = View.GONE
                itemView.btnBales.visibility = View.VISIBLE

                //label Pesan
                itemView.textView13.visibility = View.GONE
                itemView.textview12.visibility = View.GONE
                itemView.tvPesan.visibility = View.GONE


            }else if (data.tipePengajuan.toString().equals("3")){
                itemView.tvTitle.text = "Saran/Masukan/Aduan"
                itemView.textView8.visibility = View.GONE
                itemView.textWiew7.visibility = View.GONE
                itemView.tvJam.visibility = View.GONE
                itemView.textView6.text = "Direktorat"
                itemView.tvDirektorat.text = data.namaDirektorat.toString()
                itemView.btnBales.visibility = View.VISIBLE

                itemView.tvStatus.visibility = View.GONE
                itemView.imageView7.visibility = View.GONE
                //label Pesan
                itemView.textView13.visibility = View.VISIBLE
                itemView.textview12.visibility = View.VISIBLE
                itemView.tvPesan.visibility = View.VISIBLE
                itemView.tvPesan.text = data.pesan.toString()


            }



        }
    }

}