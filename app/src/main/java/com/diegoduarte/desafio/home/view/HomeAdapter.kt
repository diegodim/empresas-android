package com.diegoduarte.desafio.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.bitmap.Downsampler
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.diegoduarte.desafio.R
import com.diegoduarte.desafio.data.model.Enterprise

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.EnterpriseViewHolder>() {


    private var enterprises : List<Enterprise> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            HomeAdapter.EnterpriseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_enterprise, parent, false)
        return EnterpriseViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.EnterpriseViewHolder,
                                  position: Int) {
        holder.bindItem(enterprises[position])
    }

    override fun getItemCount(): Int = enterprises.size

    override fun onViewRecycled(holder: EnterpriseViewHolder) {

        holder.clearView()
        super.onViewRecycled(holder)
    }

    fun setList(listEnterprise: List<Enterprise>){
        enterprises = listEnterprise
        notifyDataSetChanged()
    }
    inner class EnterpriseViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val imagePhoto: ImageView = itemView.findViewById(R.id.item_image_photo)
        private val textName: TextView = itemView.findViewById(R.id.item_txt_name)
        private val textCountry: TextView = itemView.findViewById(R.id.item_txt_country)
        private val textType: TextView = itemView.findViewById(R.id.item_txt_type)

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

        }

        fun bindItem(enterprise: Enterprise) {
            Glide
                .with(itemView.context)
                .load(itemView.context.getString(R.string.url_image) + enterprise.photo)
                .placeholder(R.color.beige)
                .centerInside()
                .set(Downsampler.DECODE_FORMAT, DecodeFormat.PREFER_RGB_565)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imagePhoto)
            textName.text = enterprise.enterprise_name
            textCountry.text = enterprise.country
            textType.text = enterprise.enterprise_type.enterprise_type_name
        }

        fun clearView (){
            Glide.with(itemView.context).clear(imagePhoto)
        }

    }
}
