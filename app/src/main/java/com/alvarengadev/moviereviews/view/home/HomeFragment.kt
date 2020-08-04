package com.alvarengadev.moviereviews.view.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alvarengadev.moviereviews.R
import com.alvarengadev.moviereviews.view.home.favorites.FavoritesFragment
import com.alvarengadev.moviereviews.view.home.reviews.ReviewsFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

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
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_search_review) {
            Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }

}