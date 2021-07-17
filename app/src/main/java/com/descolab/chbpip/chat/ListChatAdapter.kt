package com.descolab.chbpip.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.descolab.chbpip.R
import com.descolab.chbpip.service.response.DataItem
import kotlinx.android.synthetic.main.item_direktorat.view.*

class ListChatAdapter(private val mContext: Context,
                      val mItems: ArrayList<DataItem>,
                      val listener: ListDirektoratListener) :
    RecyclerView.Adapter<ListChatAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater

    init {
        mInflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListChatAdapter.ViewHolder {
        val view = mInflater.inflate(R.layout.item_direktorat_chat, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return mItems.size
        //return 5
    }

    override fun onBindViewHolder(holder: ListChatAdapter.ViewHolder, position: Int) {
        if (0 <= position && position < mItems.size) {
            val data = mItems[position]
            // Bind your data here
            holder.bind(data)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: DataItem) {
            itemView.tvTitle.text = "Admin "+data.nama.toString()

//            itemView.ivGambar?.let{
//                Tools.displayImageOriginal(mContext,it,data.urlToImage.toString())
//            }

            itemView.setOnClickListener {
                listener.toDetailDirektorat(data)
            }
        }
    }
    interface ListDirektoratListener {
        fun toDetailDirektorat(item: DataItem)
    }
}