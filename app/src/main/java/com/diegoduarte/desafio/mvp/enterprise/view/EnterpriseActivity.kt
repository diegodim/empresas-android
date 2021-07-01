package com.diegoduarte.desafio.mvp.enterprise.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.bitmap.Downsampler
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.diegoduarte.desafio.R
import com.diegoduarte.desafio.base.BaseActivity
import com.diegoduarte.desafio.base.BasePresenter
import com.diegoduarte.desafio.data.model.Enterprise
import com.diegoduarte.desafio.mvp.enterprise.EnterpriseContract
import javax.inject.Inject

class EnterpriseActivity : BaseActivity(), EnterpriseContract.View {

    companion object{
        const val INTENT_EXTRA_ENTERPRISE = "enterprise"
    }
    @Inject
    lateinit var presenter: EnterpriseContract.Presenter

    private lateinit var textDescription: TextView
    private lateinit var imagePhoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.enterprise_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        textDescription = findViewById(R.id.enterprise_text_description)
        imagePhoto = findViewById(R.id.enterprise_image_photo)

        presenter.getEnterprise()
    }

    override fun getContent(): Int = R.layout.activity_enterprise

    override fun getPresenter(): BasePresenter = presenter as BasePresenter

    override fun showEnterprise(enterprise: Enterprise) {

        Glide
            .with(this)
            .load(getString(R.string.url_image) + enterprise.photo)
            .placeholder(R.color.beige)
            .centerInside()
            .set(Downsampler.DECODE_FORMAT, DecodeFormat.PREFER_RGB_565)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imagePhoto)
        supportActionBar?.title = enterprise.enterprise_name
        textDescription.text = enterprise.description

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}