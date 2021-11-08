package com.livo.nuo.view.notifications

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.ShimmerFrameLayout
import com.livo.nuo.R
import com.livo.nuo.models.NotificationModel

import com.livo.nuo.view.home.adapter.ListingAdapter
import android.text.Spannable

import android.text.style.ForegroundColorSpan

import android.graphics.Typeface

import android.text.style.StyleSpan

import android.text.SpannableString

import android.text.SpannableStringBuilder

import androidx.appcompat.widget.AppCompatTextView




class NotificationsAdapter(
    private var currAtivity: Activity,
    private var list: ArrayList<NotificationModel>
) :
    RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {

    private val LOADING = 0
    private val ITEM = 1
    private var isLoadingAdded = false

    companion object {
        var pagenuomber = 0
        var lastPagenuomber = 0
    }


    private var isSelected = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_notifications, parent, false)
        return ViewHolder(view)


    }



    override fun getItemViewType(position: Int): Int {
        return if (position == list.size - 1 && isLoadingAdded) LOADING else ITEM

//        return super.getItemViewType(position)

    }

    override fun getItemCount(): Int {
        return if (list == null) 0 else list.size
    }


    fun getItem(position: Int): NotificationModel {
        return list.get(position)
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun filterData(filterList : ArrayList<NotificationModel>){
        list = filterList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]

        holder.tvUserName.text=model.message

        if (model.name.isEmpty())
            holder.tvUserName.text=model.message
        else{
            makeTextBold(model.message,model.name,holder.tvUserName)
        }

        holder.tvTime.text=model.date

        Glide.with(currAtivity).addDefaultRequestListener(object : RequestListener<Any> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Any>?,
                isFirstResource: Boolean
            ): Boolean {
               /* holder.shimmerImagex.visibility = View.VISIBLE
//                        holder.itemView.imgProductImagex.visibility = View.INVISIBLE
                holder.shimmerImagex.startShimmer()*/
                return false
            }

            override fun onResourceReady(
                resource: Any?,
                model: Any?,
                target: Target<Any>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
               /* holder.shimmerImagex.visibility = View.GONE
                holder.imgshimmerImage.visibility = View.GONE
                holder.imgProductImagex.visibility = View.VISIBLE
//                        holder.itemView.imgProductImagex.visibility = View.VISIBLE
                holder.shimmerImagex.stopShimmer()*/
                return false
            }

        })
            .load(model.image).placeholder(currAtivity.getDrawable(R.drawable.grey_round_shape)).
            error(currAtivity.getDrawable(R.drawable.grey_round_shape)).
            into(holder.imgUserImage)
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


        var imgUserImage: ImageView
        var tvUserName: TextView
        var tvTime: TextView

        init {

            tvUserName = ItemView.findViewById(R.id.tvUserName)
            imgUserImage=ItemView.findViewById(R.id.imgUserImage)
            tvTime=ItemView.findViewById(R.id.tvTime)
        }
    }

    inner class ProgressViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        init {

        }
    }

    fun makeTextBold(sentence: String, word: String, textView: TextView) {
        val builder = SpannableStringBuilder()
        val startIndex = sentence.indexOf(word.trim { it <= ' ' })
        val endIndex = startIndex + word.trim { it <= ' ' }.length
        val spannableString = SpannableString(sentence)
        val boldSpan = StyleSpan(Typeface.BOLD)
        spannableString.setSpan(
            boldSpan,
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        ) //To make text Bold
        spannableString.setSpan(
            ForegroundColorSpan(currAtivity.resources.getColor(R.color.colorPrimary)),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        ) //To change color of text
        builder.append(spannableString)
        textView.setText(builder, TextView.BufferType.SPANNABLE)
    }

}