package com.livo.nuo.view.prelogin.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.livo.nuo.R
import com.livo.nuo.models.CountryCodeModel
import com.livo.nuo.view.prelogin.Login_Activity


class CountryCodeAdapter(private var currAtivity : Activity, private var list: ArrayList<CountryCodeModel>) :
    RecyclerView.Adapter<CountryCodeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryCodeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_country_code,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CountryCodeAdapter.ViewHolder, position: Int) {
        val model = list[position]
        holder.tvCountryName.setText(model.country_name)
        holder.tvCountryCode.setText(model.country_code)
        Glide.with(currAtivity).load(model.image).into(holder.imgCountryCodeImage)


//        if(model.isSelected){
//            holder.itemView.rlInternal.background = currAtivity.resources.getDrawable(R.drawable.button_blue_shape_lesser_round_ripple_effect)
//            holder.itemView.tvCategoryName.setTextColor(currAtivity.resources.getColor(R.color.white))
//        }else{
//            holder.itemView.rlInternal.background = currAtivity.resources.getDrawable(R.drawable.button_white_shape_lesser_round_ripple_effect)
//            holder.itemView.tvCategoryName.setTextColor(currAtivity.resources.getColor(R.color.dark_grey))
//
//        }

        holder.itemView.setOnClickListener{
//            model.isSelected = !model.isSelected
            notifyItemChanged(position)
            if(currAtivity is Login_Activity){
               (currAtivity as Login_Activity).setSelectedCountryCode(model)
            }
            /*else if(currAtivity is SenderProfileActivity){
                (currAtivity as SenderProfileActivity).setSelectedCountryCode(model)
            }else if(currAtivity is TransporterApplicationActivity){
                (currAtivity as TransporterApplicationActivity).setSelectedCountryCode(model)
            }*/

        }
    }

    fun filterData(filterList : ArrayList<CountryCodeModel>){
        list = filterList
        notifyDataSetChanged()
    }



    inner class ViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {
        var tvCountryName=ItemView.findViewById<TextView>(R.id.tvCountryName)
        var tvCountryCode=ItemView.findViewById<TextView>(R.id.tvCountryCode)
        var imgCountryCodeImage=ItemView.findViewById<ImageView>(R.id.imgCountryCodeImage)
        init {

        }
    }

}