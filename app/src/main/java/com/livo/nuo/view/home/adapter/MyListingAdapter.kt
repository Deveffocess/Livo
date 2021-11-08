package com.livo.nuoo.view.home.adapter

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.livo.nuo.R
import com.livo.nuo.manager.SessionManager
import com.livo.nuo.models.DateModel
import com.livo.nuo.models.ProductModel
import com.livo.nuo.utility.AppUtils
import com.livo.nuo.utility.MyAppSession

import android.graphics.Movie
import android.media.Image
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.card.MaterialCardView


class MyListingAdapter(
    private var currAtivity: Activity,
    private var list: ArrayList<ProductModel>, private var from: Int
) :
    RecyclerView.Adapter<MyListingAdapter.ViewHolder>() {

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
            .inflate(R.layout.row_mylisting_new, parent, false)
        return ViewHolder(view)


    }



override fun getItemViewType(position: Int): Int {
    return if (position == list.size - 1 && isLoadingAdded) LOADING else ITEM

//        return super.getItemViewType(position)

}

override fun getItemCount(): Int {
    return if (list == null) 0 else list.size
}


fun getItem(position: Int): ProductModel {
    return list.get(position)
}

fun clear() {
    list.clear()
    notifyDataSetChanged()
}


override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val model = list[position]

   if (model.status.equals("Expired") || model.status.equals("Suspended"))
   {
       holder.rlBottomLayout.visibility=View.GONE

       holder.tvRecievedOffer
       if(model.status.equals("Suspended"))
           holder.mcvOfferCell.strokeColor =currAtivity.resources.getColor(R.color.danger)
       else if(model.status.equals("Expired"))
           holder.mcvOfferCell.strokeColor =currAtivity.resources.getColor(R.color.black_20_opacity)

       var user_detail_any=model.user_details.first_name

       if (user_detail_any.isEmpty())
       {
           holder.tvRecievedOfferlabel.visibility=View.VISIBLE
           holder.tvRecievedOffer.visibility=View.VISIBLE
           holder.tvRecievedOfferRecievedLabel.visibility=View.VISIBLE

           holder.imgTransporterImage.visibility=View.GONE
           holder.tvTransporterStatus.visibility=View.GONE
           holder.tvTransporterName.visibility=View.GONE

           holder.tvRecievedOffer.text=model.biddings.toString()

       }
       else{
           holder.tvRecievedOfferlabel.visibility=View.GONE
           holder.tvRecievedOffer.visibility=View.GONE
           holder.tvRecievedOfferRecievedLabel.visibility=View.GONE

           holder.imgTransporterImage.visibility=View.VISIBLE
           holder.tvTransporterStatus.visibility=View.VISIBLE
           holder.tvTransporterName.visibility=View.VISIBLE

           holder.tvTransporterName.text=model.user_details.first_name+" "+model.user_details.last_name
           holder.tvTransporterStatus.text=model.user_details.status_text

           Glide.with(currAtivity).load(model.user_details.profile_image)
               .into(holder.imgTransporterImage)
       }

//      Glide.with(currAtivity).load(model.images[0].thumb_image).into(holder.itemView.imgProductImagex)
   }

    else if(model.status.equals("Ongoing"))
   {
        holder.imgForward.visibility=View.GONE
       holder.tvRecievedOfferlabel.visibility=View.GONE
       holder.tvRecievedOffer.visibility=View.GONE
       holder.tvRecievedOfferRecievedLabel.visibility=View.GONE

       holder.imgTransporterImage.visibility=View.VISIBLE
       holder.tvTransporterStatus.visibility=View.VISIBLE
       holder.tvTransporterName.visibility=View.VISIBLE

       holder.mcvOfferCell.strokeColor =currAtivity.resources.getColor(R.color.warning)


       holder.tvTransporterName.text=model.user_details.first_name+" "+model.user_details.last_name
       holder.tvTransporterStatus.text=model.user_details.status_text


       Glide.with(currAtivity).load(model.user_details.profile_image)
           .error(R.drawable.tesing_user_icon)
           .into(holder.imgTransporterImage)
    }

   else if(model.status.equals("Completed"))
   {
       holder.rlBottomLayout.visibility=View.GONE

       holder.imgForward.visibility=View.GONE
       holder.tvRecievedOfferlabel.visibility=View.GONE
       holder.tvRecievedOffer.visibility=View.GONE
       holder.tvRecievedOfferRecievedLabel.visibility=View.GONE

       holder.imgTransporterImage.visibility=View.VISIBLE
       holder.tvTransporterStatus.visibility=View.VISIBLE
       holder.tvTransporterName.visibility=View.VISIBLE
       holder.mcvOfferCell.strokeColor =currAtivity.resources.getColor(R.color.success)


       holder.tvTransporterName.text=model.user_details.first_name+" "+model.user_details.last_name
       holder.tvTransporterStatus.text=model.user_details.status_text


       Glide.with(currAtivity).load(model.user_details.profile_image)
           .error(R.drawable.tesing_user_icon)
           .into(holder.imgTransporterImage)
   }

   else if(model.status.equals("Published"))
   {

       holder.rlBottomLayout.visibility=View.GONE
       holder.mcvOfferCell.strokeColor =currAtivity.resources.getColor(R.color.black_40_opacity)

           holder.tvRecievedOfferlabel.visibility=View.VISIBLE
           holder.tvRecievedOffer.visibility=View.VISIBLE
           holder.tvRecievedOfferRecievedLabel.visibility=View.VISIBLE

           holder.imgTransporterImage.visibility=View.GONE
           holder.tvTransporterStatus.visibility=View.GONE
           holder.tvTransporterName.visibility=View.GONE

           holder.tvRecievedOffer.text=model.biddings.toString()
   }



    if(model.title.length > 35){
        holder.tvTitle.text = model.title.substring(0,34)+"..."

    }else{
        holder.tvTitle.text = model.title
    }
    if(model.price.length > 8){
        holder.tvPrice1x.text = model.price.substring(0,8)+" KR"
    }else{
        holder.tvPrice1x.text = model.price+" KR"
    }

    holder.shimmerImage.visibility = View.VISIBLE
    holder.shimmerImage.startShimmer()

    Glide.with(currAtivity).addDefaultRequestListener(object : RequestListener<Any>{
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Any>?,
            isFirstResource: Boolean
        ): Boolean {
            holder.shimmerImage.visibility = View.VISIBLE
//                        holder.itemView.imgProductImagex.visibility = View.INVISIBLE
            holder.shimmerImage.startShimmer()
            return false
        }

        override fun onResourceReady(
            resource: Any?,
            model: Any?,
            target: Target<Any>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            holder.shimmerImage.visibility = View.GONE
            holder.imgshimmerImage.visibility = View.GONE
            holder.imgProductImage.visibility = View.VISIBLE
//                        holder.itemView.imgProductImagex.visibility = View.VISIBLE
            holder.shimmerImage.stopShimmer()
            return false
        }

    })
        .load(model.listing_images).placeholder(currAtivity.getDrawable(R.drawable.grey_round_shape)).
        error(currAtivity.getDrawable(R.drawable.grey_round_shape)).
        into(holder.imgProductImage)


}

inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

    var shimmerImage:ShimmerFrameLayout

    var tvTitle: TextView
    var rlBottomLayout: RelativeLayout
    var imgProductImage: ImageView
    var rlReievedOffer:RelativeLayout

    var imgForward: ImageView
    var tvRecievedOffer: TextView
    var tvTransporterName: TextView
    var imgTransporterImage: ImageView
    var tvTransporterStatus: TextView
    var tvPrice1x:TextView
    var tvRecievedOfferlabel: TextView
    var imgshimmerImage:ImageView
    var tvRecievedOfferRecievedLabel: TextView

    var mcvOfferCell:MaterialCardView

    init {
        shimmerImage=ItemView.findViewById(R.id.shimmerImage)
        tvPrice1x=ItemView.findViewById(R.id.tvPrice1x)
        imgProductImage = ItemView.findViewById(R.id.imgProductImage)
        rlBottomLayout = ItemView.findViewById(R.id.rlBottomLayout)
        rlReievedOffer=ItemView.findViewById(R.id.rlReievedOffer)
        tvTitle = ItemView.findViewById(R.id.tvTitle)
        tvRecievedOffer = ItemView.findViewById(R.id.tvRecievedOffer)
        imgTransporterImage = ItemView.findViewById(R.id.imgTransporterImage)
        tvTransporterName = ItemView.findViewById(R.id.tvTransporterName)
        tvTransporterStatus = ItemView.findViewById(R.id.tvTransporterStatus)
        imgForward = ItemView.findViewById(R.id.imgForward)
        tvRecievedOfferlabel = ItemView.findViewById(R.id.tvRecievedOfferlabel)
        imgshimmerImage=ItemView.findViewById(R.id.imgshimmerImage)
        tvRecievedOfferRecievedLabel = ItemView.findViewById(R.id.tvRecievedOfferRecievedLabel)
        mcvOfferCell=ItemView.findViewById(R.id.mcvOfferCell)

    }
}

inner class ProgressViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

    init {

    }
}

}
