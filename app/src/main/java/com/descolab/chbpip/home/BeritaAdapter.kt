package com.descolab.chbpip.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.descolab.chbpip.R
import com.descolab.chbpip.helper.Tools
import com.descolab.chbpip.service.response.Berita
import kotlinx.android.synthetic.main.item_berita.view.*

class BeritaAdapter(private val mContext: Context,
                    val mItems: ArrayList<Berita>,
                    val listener: ListCategoryListener) :
    RecyclerView.Adapter<BeritaAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater

    init {
        mInflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaAdapter.ViewHolder {
        val view = mInflater.inflate(R.layout.item_berita, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return mItems.size
        //return 5
    }

    override fun onBindViewHolder(holder: BeritaAdapter.ViewHolder, position: Int) {
        if (0 <= position && position < mItems.size) {
            val data = mItems[position]
            // Bind your data here
            holder.bind(data)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Berita) {
            itemView.tvTitle.text = data.title.toString()
            itemView.iv_berita?.let{
                Tools.displayImageOriginal(mContext,it,data.img.toString())
            }
            Log.d("Cek","isi FOto"+data.img)
            itemView.setOnClickListener {
                listener.toDetailCategory(data)
            }
        }
    }
    interface ListCategoryListener {
        fun toDetailCategory(item: Berita)
    }
}