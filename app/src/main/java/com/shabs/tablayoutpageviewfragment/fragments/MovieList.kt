package com.shabs.tablayoutpageviewfragment.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shabs.pagepoc.MainListAdapter
import com.shabs.pagepoc.MainViewModel
import com.shabs.pagepoc.MainViewModelFactory
import com.shabs.tablayoutpageviewfragment.APIService
import com.shabs.tablayoutpageviewfragment.R
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.coroutines.flow.collect


class MovieList : Fragment() {

    lateinit var viewModel: MainViewModel
    lateinit var mainListAdapter: MainListAdapter
    lateinit var recyclerView: RecyclerView
    private var movieName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieName = it.getString("movie")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupList()
        setupView()
    }

    private fun setupView() {
        lifecycleScope.launchWhenCreated {
            viewModel.listData.collect {
                mainListAdapter.submitData(it)
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, MainViewModelFactory(APIService.getApiService()))[MainViewModel::class.java]
    }

    private fun setupList() {
        mainListAdapter = MainListAdapter()
        recyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mainListAdapter
        }

//        recyclerview.adapter = mainListAdapter.withLoadStateHeaderAndFooter(
//            header = HeaderFooterAdapter { mainListAdapter.retry() },
//            footer = HeaderFooterAdapter { mainListAdapter.retry() })
//
//        mainListAdapter.addLoadStateListener {
//            if (it.refresh == LoadState.Loading) {
//                Log.d("TAG", "loading")
//                progressBar.visibility = View.VISIBLE
//            } else {
//                progressBar.visibility = View.INVISIBLE
//                Log.d("TAG", " not loading")
//            }
//        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    companion object {
        @JvmStatic
        fun getInstance(movieName: String) = MovieList().apply {
            arguments = Bundle().apply {
                putString("movie", movieName)
            }
        }
    }
}