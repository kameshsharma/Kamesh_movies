package com.example.lowesmovies.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ccom.example.lowesmovies.models.Result
import com.google.android.material.snackbar.Snackbar
import com.example.lowesmovies.databinding.ActivityListBinding
import com.example.lowesmovies.models.Results
import com.example.lowesmovies.ui.adapters.MovieAdapter
import com.example.lowesmovies.ui.viewModels.ListingViewModel
import com.example.lowesmovies.utils.ImageDeoration
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListingsActivity : AppCompatActivity(), MovieAdapter.OnItemCardClickListener {

    companion object {
        private const val SPACE: Int = 15;
    }

    private lateinit var binding: ActivityListBinding
    private val list = ArrayList<Results>()
    private val viewModel by viewModels<ListingViewModel>()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater);
        setContentView(binding.root)

        init()
        subscribeUi()
    }

    private fun init() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvMovie.layoutManager = layoutManager

        val dividerItemDecoration = ImageDeoration(
            binding.rvMovie.context,
            SPACE
        )

        binding.rvMovie.addItemDecoration(dividerItemDecoration)
        movieAdapter = MovieAdapter(this, list, this)
        binding.rvMovie.adapter = movieAdapter
    }

    private fun subscribeUi() {
        viewModel.movieList.observe(this, Observer { result ->

            when (result.status) {
                ccom.example.lowesmovies.models.Result.Status.SUCCESS -> {
                    result.data?.results?.let { list ->
                        movieAdapter.updateData(list)
                    }
                    binding.loading.visibility = View.GONE
                }

                ccom.example.lowesmovies.models.Result.Status.ERROR -> {
                    result.message?.let {
                        showError(it)
                    }
                    binding.loading.visibility = View.GONE
                }

                Result.Status.LOADING -> {
                    binding.loading.visibility = View.VISIBLE
                }
            }

        })
    }

    private fun showError(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_INDEFINITE).setAction("DISMISS") {
        }.show()
    }

    override fun onClick(movies: Results) {


        val intent = Intent(baseContext, DetailActivity::class.java)
        intent.putExtra("display_title", movies.display_title)
        intent.putExtra("multimedia", movies.multimedia.src)
        intent.putExtra("headline", movies.headline)
        intent.putExtra("summary_short", movies.summary_short)
        intent.putExtra("byline", movies.byline)
        intent.putExtra("publication_date", movies.publication_date)
        startActivity(intent)

    }

}