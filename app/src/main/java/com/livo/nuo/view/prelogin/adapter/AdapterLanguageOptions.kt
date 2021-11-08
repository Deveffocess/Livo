package com.livo.nuo.view.prelogin.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.livo.nuo.R
import com.livo.nuo.models.CountryCodeModel
import com.livo.nuo.view.prelogin.Login_Activity


class AdapterLanguageOptions(private var currAtivity : Activity, private var list: ArrayList<CountryCodeModel>) :
        RecyclerView.Adapter<AdapterLanguageOptions.ViewHolder>() {

    var isSelected = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterLanguageOptions.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_language_options,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AdapterLanguageOptions.ViewHolder, position: Int) {
        val model = list[position]
        holder.tv_language.text = model.country_name
        Glide.with(currAtivity).load(model.image).into(holder.img_country)


        if(isSelected == model.id){
            holder.ll_outer_body.background = currAtivity.resources.getDrawable(R.drawable.red_circle_border_shape)
//            holder.itemView.tvCategoryName.setTextColor(currAtivity.resources.getColor(R.color.white))
        }else{
            holder.ll_outer_body.background = currAtivity.resources.getDrawable(R.drawable.white_circular_shape)
//            holder.itemView.tvCategoryName.setTextColor(currAtivity.resources.getColor(R.color.dark_grey))

        }

        holder.itemView.setOnClickListener{
            isSelected = model.id
//            model.isSelected = !model.isSelected
            notifyDataSetChanged()
            if(currAtivity is Login_Activity){
                (currAtivity as Login_Activity).click(model)
            }

        }
    }

    fun filterData(filterList : ArrayList<CountryCodeModel>){
        list = filterList
        notifyDataSetChanged()
    }



    inner class ViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {

        var ll_outer_body=ItemView.findViewById<LinearLayout>(R.id.ll_outer_body)
        var tv_language=ItemView.findViewById<TextView>(R.id.tv_language)
        var img_country=ItemView.findViewById<ImageView>(R.id.img_country)

        init {

        }
    }

}