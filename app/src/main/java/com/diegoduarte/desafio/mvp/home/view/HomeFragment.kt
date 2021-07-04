package com.diegoduarte.desafio.mvp.home.view


import android.content.Intent
import android.os.Bundle
import android.view.*

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegoduarte.desafio.R
import com.diegoduarte.desafio.base.BaseFragment
import com.diegoduarte.desafio.base.BaseObserver
import com.diegoduarte.desafio.base.BasePresenter
import com.diegoduarte.desafio.data.model.Enterprise
import com.diegoduarte.desafio.databinding.FragmentHomeBinding
import com.diegoduarte.desafio.mvp.enterprise.view.EnterpriseActivity
import com.diegoduarte.desafio.mvp.home.HomeContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class HomeFragment : BaseFragment<FragmentHomeBinding>(),
    HomeContract.View {

    private lateinit var searchView: SearchView
    private lateinit var rxSearchView: RxSearchView

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    // Inject the object of presenter
    @Inject
    lateinit var presenter: HomeContract.Presenter

    // Instance all view objects
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home, container, false)

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)

        initializeRecyclerView()
        presenter.onCreate()
        setHasOptionsMenu(true)
        return binding.root
    }


    // Set the presenter on hte BaseActivity
    override fun getPresenter(): BasePresenter = presenter as BasePresenter


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Prepare search view
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
        val searchViewMenuItem: MenuItem = menu.findItem(R.id.action_search)
        searchView = searchViewMenuItem.actionView as SearchView

        // Instance the searchView Observable
        rxSearchView = RxSearchView(searchView, Schedulers.io(),
            AndroidSchedulers.mainThread())
        val disposable = rxSearchView.execute(SearchViewObserver(),null)
        (presenter as BasePresenter).addDisposable(disposable)

        // Logic for search view states
        searchViewMenuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                searchView.queryHint = getString(R.string.content_search_view)
                binding.layoutWelcome.visibility = View.GONE
                return true
            }
            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                presenter.onCreate()
                return true
            }
        })


    }

    // Setup recycler View
    private fun initializeRecyclerView() {

        //binding.recyclerView = findViewById(R.id.recycler_view)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.recycledViewPool.clear()
        val layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setItemViewCacheSize(4)
        binding.recyclerView.adapter = HomeAdapter(this)

    }

    // Input data on recyclerView
    override fun showEnterprises(enterprises: List<Enterprise>) {
        if(searchView.query.isNotEmpty()) {
            binding.recyclerView.visibility = View.VISIBLE
            binding.layoutWelcome.visibility = View.GONE
            binding.layoutSearchEmpty.visibility = View.GONE
            (binding.recyclerView.adapter as HomeAdapter).setList(enterprises)
        }
    }

    // Show when has no value on the search
    override fun showEmptySearch() {
        if(searchView.query.isNotEmpty()) {
            binding.recyclerView.visibility = View.GONE
            binding.layoutWelcome.visibility = View.GONE
            binding.layoutSearchEmpty.visibility = View.VISIBLE
        }
    }

    // Show the first layout of activity
    override fun showHomeLayout(){
        binding.recyclerView.visibility = View.GONE
        binding.layoutWelcome.visibility = View.VISIBLE
        binding.layoutSearchEmpty.visibility = View.GONE

    }

    // return back to LoginActivity if the token expire
    override fun returnToLogin() {
        Navigation
            .createNavigateOnClickListener(R.id.action_loginFragment_to_homeFragment)
    }

    override fun onItemClick(enterprise: Enterprise) {
        val intent = Intent(activity, EnterpriseActivity::class.java)
        intent.putExtra(EnterpriseActivity.INTENT_EXTRA_ENTERPRISE, enterprise)
        startActivity(intent)
    }

    inner class SearchViewObserver: BaseObserver<String>(){
        override fun onNext(t: String) {
            super.onNext(t)
            presenter.searchByName(t)
        }
    }

    override var dataBiding: FragmentHomeBinding?
        get() = binding
        set(value) {
            _binding = value
        }
}