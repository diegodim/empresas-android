package com.diegoduarte.desafio.home.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegoduarte.desafio.R
import com.diegoduarte.desafio.base.BaseActivity
import com.diegoduarte.desafio.base.BasePresenter
import com.diegoduarte.desafio.data.model.Enterprise
import com.diegoduarte.desafio.home.HomeContract
import com.diegoduarte.desafio.utils.SearchObservable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class HomeActivity : BaseActivity(), HomeContract.View {

    companion object {
        const val INTENT_EXTRA_TOKEN= "token"
    }

    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.home_toolbar))
        initializeRecyclerView()
    }

    override fun getContent(): Int = R.layout.activity_home

    override fun getPresenter(): BasePresenter = presenter as BasePresenter

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        val searchViewMenuItem: MenuItem = menu.findItem(R.id.action_search)
        searchView = searchViewMenuItem.actionView as SearchView
        val disposable = SearchObservable()
            .fromView(searchView)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe{ presenter.searchByName(it.toString())}
        (presenter as BasePresenter).addDisposable(disposable!!)
        return super.onPrepareOptionsMenu(menu)
    }

    private fun initializeRecyclerView() {

        recyclerView = findViewById(R.id.home_recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.recycledViewPool.clear()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.setItemViewCacheSize(4)
        recyclerView.adapter = HomeAdapter()

    }

    override fun showEnterprises(enterprises: List<Enterprise>) {
        (recyclerView.adapter as HomeAdapter).setList(enterprises)
    }
}