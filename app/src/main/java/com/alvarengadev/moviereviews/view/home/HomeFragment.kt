package com.alvarengadev.moviereviews.view.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.alvarengadev.moviereviews.R
import com.alvarengadev.moviereviews.view.home.favorites.FavoritesFragment
import com.alvarengadev.moviereviews.view.home.reviews.ReviewsFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val fragmentPagerItemAdapter = FragmentPagerItemAdapter(
            childFragmentManager,
            FragmentPagerItems.with(context)
                .add("Reviews", ReviewsFragment::class.java)
                .add("Favorites", FavoritesFragment::class.java)
                .create()
        )

        viewpager_tab_layout_home.adapter = fragmentPagerItemAdapter
        tab_layout_home.setViewPager(viewpager_tab_layout_home)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search_reviews, menu)


        val searchItem = menu.findItem(R.id.menu_search_review)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(this)
        searchView.setOnCloseListener {
            homeViewModel.searchReviewData.value = ""
            false
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

   override fun onQueryTextSubmit(textSubmit: String?): Boolean {
       homeViewModel.searchReviewData.value = textSubmit
       return false
   }

   override fun onQueryTextChange(textChange: String?): Boolean {
       homeViewModel.searchReviewData.value = textChange
       return false
   }

}