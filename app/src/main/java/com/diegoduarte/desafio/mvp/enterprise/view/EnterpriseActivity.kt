package com.diegoduarte.desafio.mvp.enterprise.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.bitmap.Downsampler
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.diegoduarte.desafio.R
import com.diegoduarte.desafio.base.BaseActivity
import com.diegoduarte.desafio.base.BasePresenter
import com.diegoduarte.desafio.data.model.Enterprise
import com.diegoduarte.desafio.databinding.ActivityEnterpriseBinding
import com.diegoduarte.desafio.mvp.enterprise.EnterpriseContract
import javax.inject.Inject

class EnterpriseActivity : BaseActivity(), EnterpriseContract.View {

    companion object{
        const val INTENT_EXTRA_ENTERPRISE = "enterprise"
    }
    @Inject
    lateinit var presenter: EnterpriseContract.Presenter

    private lateinit var binding: ActivityEnterpriseBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_enterprise)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        presenter.getEnterprise()
    }


    override fun getPresenter(): BasePresenter = presenter as BasePresenter

    override fun showEnterprise(enterprise: Enterprise) {

        Glide
            .with(this)
            .load(getString(R.string.url_image) + enterprise.photo)
            .placeholder(R.color.beige)
            .centerInside()
            .set(Downsampler.DECODE_FORMAT, DecodeFormat.PREFER_RGB_565)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imagePhoto)
        binding.enterprise = enterprise

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}