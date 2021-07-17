package com.descolab.chbpip.chat

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.descolab.chbpip.R
import com.descolab.chbpip.form_luring.FormLuringActivity
import com.descolab.chbpip.helper.DataConfig
import com.descolab.chbpip.service.response.DataItem
import kotlinx.android.synthetic.main.activity_form_luring.*
import kotlinx.android.synthetic.main.item_percakapan_admin.view.*
import kotlinx.android.synthetic.main.item_percakapan_myself.view.*

class ChatPercakapanAdapter(private val mContext: Context,
                            private val items: List<DataItem>) :
    RecyclerView.Adapter<ChatPercakapanAdapter.BaseViewHolder<DataItem>>() {

    private val mInflater: LayoutInflater

    init {
        mInflater = LayoutInflater.from(mContext)
    }
    companion object {
        val VIEW_TYPE_MY_SELF = 0
        val VIEW_TYPE_USER = 1
        val VIEW_TYPE_BTN = 2
    }

    override fun getItemViewType(position: Int): Int {
        val data = items.get(position) as DataItem

        return if (data.from.toString().equals(DataConfig.getString(DataConfig.USER_ID))) {
            // If the current user is the sender of the message
            VIEW_TYPE_MY_SELF
        } else {
            // If some other user sent the message
            VIEW_TYPE_USER

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DataItem> {
        //val view = mInflater.inflate(R.layout.item_percakapan_aparat, parent, false)
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_MY_SELF -> {
                val view = layoutInflater.inflate(R.layout.item_percakapan_myself,  parent, false)
                return ViewHolderChatItemMySelf(view)
            }
            else -> {
                val view = layoutInflater.inflate(R.layout.item_percakapan_admin,  parent, false)
                return ViewHolderChatItemUser(view)
            }
        }

//        return ViewHolder(view)
    }

    abstract class BaseViewHolder<DataItem>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: DataItem)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<DataItem>, position: Int) {
        if (0 <= position && position < items.size) {
            val data = items[position]
            // Bind your data here
            holder.bind(data)
        }

//
//        val chat = items[position]
//
//
//            when (holder.itemViewType) {
//                VIEW_TYPE_MY_SELF -> {
//                    val viewHolderChatItemMySelf = holder as ViewHolderChatItemMySelf
//                    viewHolderChatItemMySelf.textViewDateTime.text = chat.getNama()
//                    viewHolderChatItemMySelf.textViewMessage.text = chat.getKomentar()
//                }
//                else -> {
//                    val viewHolderChatUser = holder as ViewHolderChatItemUser
//                    viewHolderChatUser.textViewDateTime.text = chat.getNama()
//                    viewHolderChatUser.textViewMessage.text = chat.getKomentar()
//                }
//            }

    }


//    open inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class ViewHolderChatItemMySelf(itemView: View) :  BaseViewHolder<DataItem>(itemView) {

        override fun bind(data: DataItem) {
            itemView.text_message_name_my_self.text = data.nama
            itemView.text_message_body_my_self.text = data.isi.toString()
        }

    }
    inner class ViewHolderChatItemUser(itemView: View) :  BaseViewHolder<DataItem>(itemView) {

        override fun bind(data: DataItem) {
            itemView.text_message_name.text = "Admin"
            itemView.text_message_body.text = data.isi.toString()
            if (data.status.toString().equals("1")){
                itemView.btnFormLuring.visibility = View.VISIBLE
                itemView.btnFormLuring.setOnClickListener {
                    val i = Intent(mContext, FormLuringActivity::class.java)
                    i.putExtra("idDirektorat",data.from.toString())
                    mContext.startActivity(i)
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }

}