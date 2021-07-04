package com.diegoduarte.desafio.mvp.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.bitmap.Downsampler
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.diegoduarte.desafio.R
import com.diegoduarte.desafio.data.model.Enterprise
import com.diegoduarte.desafio.databinding.ItemEnterpriseBinding
import com.diegoduarte.desafio.mvp.home.HomeContract

class HomeAdapter(private val view: HomeContract.View): RecyclerView.Adapter<HomeAdapter.EnterpriseViewHolder>() {

    // List of recyclerView itens
    private var enterprises : List<Enterprise> = ArrayList()

    // Inflate a item of recyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            HomeAdapter.EnterpriseViewHolder {

        val binding = ItemEnterpriseBinding.inflate(LayoutInflater.from(parent.context)
            , parent, false)
        return EnterpriseViewHolder(binding)
    }

    // Set value of item of recyclerView
    override fun onBindViewHolder(holder: HomeAdapter.EnterpriseViewHolder,
                                  position: Int) {
        holder.bind(enterprises[position])
    }

    // Get the quantity of the recyclerView
    override fun getItemCount(): Int = enterprises.size

    // recycle items from memory
    override fun onViewRecycled(holder: EnterpriseViewHolder) {

        holder.clearView()
        super.onViewRecycled(holder)
    }

    // Set data list an update recyclerView
    fun setList(listEnterprise: List<Enterprise>){
        enterprises = listEnterprise
        notifyDataSetChanged()
    }

    // Set the value view objects
    inner class EnterpriseViewHolder(private val binding: ItemEnterpriseBinding) :
        RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {


        init{
            binding.card.setOnClickListener(this)
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            view.onItemClick(enterprises[adapterPosition])
        }

        fun bind(enterprise: Enterprise) {
            Glide
                .with(itemView.context)
                .load(itemView.context.getString(R.string.url_image) + enterprise.photo)
                .placeholder(R.color.beige)
                .centerInside()
                .set(Downsampler.DECODE_FORMAT, DecodeFormat.PREFER_RGB_565)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imagePhoto)
            binding.enterprise = enterprise
        }

        fun clearView (){
            Glide.with(itemView.context).clear(binding.imagePhoto)
        }

    }
}
