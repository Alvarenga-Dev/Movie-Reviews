package com.alvarengadev.moviereviews.view.home.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.alvarengadev.moviereviews.R
import com.alvarengadev.moviereviews.data.domain.Review
import com.alvarengadev.moviereviews.extensions.navigateActionDirectionWithAnimation
import com.alvarengadev.moviereviews.utils.visibilities
import com.alvarengadev.moviereviews.view.home.HomeFragmentDirections
import com.alvarengadev.moviereviews.view.home.HomeViewModel
import com.alvarengadev.moviereviews.view.home.common.OnReviewClickListener
import com.alvarengadev.moviereviews.view.home.favorites.adapter.FavoritesAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment(), OnReviewClickListener {

    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getMoviesCriticsReviewFavoritesData.observe(viewLifecycleOwner, Observer {
            it?.let { favorites ->
                if (favorites.isEmpty()) {
                    visibilities(invisible = rcy_favorites, visible = iv_list_favorites_empty)
                } else {
                    visibilities(invisible = iv_list_favorites_empty, visible = rcy_favorites)
                    val favoritesAdapter = FavoritesAdapter(favorites)

                    homeViewModel.searchReviewData.observe(viewLifecycleOwner, Observer { titleSearch ->
                        favoritesAdapter.filter.filter(titleSearch)
                    })

                    favoritesAdapter.setOnItemClickListener(this)
                    rcy_favorites.apply {
                        adapter = favoritesAdapter
                        layoutManager = GridLayoutManager(context, 2)
                    }
                }
            }
        })

        homeViewModel.loadReviewsFavorites()
    }

    override fun onItemClick(review: Review) {
        val directions = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(review)
        findNavController().navigateActionDirectionWithAnimation(directions)
    }

}