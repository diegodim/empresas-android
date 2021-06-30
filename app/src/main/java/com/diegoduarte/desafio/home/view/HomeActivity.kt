package com.diegoduarte.desafio.home.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegoduarte.desafio.R
import com.diegoduarte.desafio.base.BaseActivity
import com.diegoduarte.desafio.base.BasePresenter
import com.diegoduarte.desafio.data.model.Enterprise
import com.diegoduarte.desafio.home.HomeContract
import com.diegoduarte.desafio.login.view.LoginActivity
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
    private lateinit var layoutHome: View
    private lateinit var layoutSearchEmpty: View

    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.home_toolbar))
        layoutHome = findViewById(R.id.home_layout_welcome)
        layoutSearchEmpty = findViewById(R.id.home_layout_search_empty)
        initializeRecyclerView()
        presenter.onCreate()
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

        searchViewMenuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                searchView.queryHint = getString(R.string.content_search_view)
                layoutHome.visibility = View.GONE
                return true
            }
            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                presenter.onCreate()
                return true
            }
        })

        return super.onPrepareOptionsMenu(menu)
    }

    private fun initializeRecyclerView() {

        recyclerView = findViewById(R.id.home_recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.recycledViewPool.clear()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.setItemViewCacheSize(4)
        recyclerView.adapter = HomeAdapter()

    }

    override fun showEnterprises(enterprises: List<Enterprise>) {
        if(searchView.query.isNotEmpty()) {
            recyclerView.visibility = View.VISIBLE
            layoutHome.visibility = View.GONE
            layoutSearchEmpty.visibility = View.GONE
            (recyclerView.adapter as HomeAdapter).setList(enterprises)
        }
    }

    override fun showEmptySearch() {
        if(searchView.query.isNotEmpty()) {
            recyclerView.visibility = View.GONE
            layoutHome.visibility = View.GONE
            layoutSearchEmpty.visibility = View.VISIBLE
        }
    }

    override fun showHomeLayout(){
        recyclerView.visibility = View.GONE
        layoutHome.visibility = View.VISIBLE
        layoutSearchEmpty.visibility = View.GONE

    }

    override fun returnToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        Toast.makeText(this,"A sessão expirou.",Toast.LENGTH_LONG).show()
    }
}