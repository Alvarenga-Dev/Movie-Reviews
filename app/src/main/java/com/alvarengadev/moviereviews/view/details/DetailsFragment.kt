package com.alvarengadev.moviereviews.view.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alvarengadev.moviereviews.R
import com.alvarengadev.moviereviews.data.domain.Review
import com.alvarengadev.moviereviews.view.details.dialog.add.DialogAddFavorite
import com.alvarengadev.moviereviews.view.details.dialog.remove.DialogRemoveFavorite
import com.alvarengadev.moviereviews.view.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var review: Review

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        review = args.review
        initMovieCriticsReview()

        homeViewModel.isRemoveData.observe(viewLifecycleOwner, Observer {isRemove ->
            if (isRemove) {
                findNavController().popBackStack()
                homeViewModel.isRemoveData.value = false
            }
        })
    }

    private fun initMovieCriticsReview() {
        tv_title_movie_details.text = review.title
        tv_title_critic_details.text = review.headline
        tv_author_critics_details.text = review.author
        tv_summary_movie_details.text = review.summary

        btn_critics_open_link_details.setOnClickListener(openCriticsReview())
    }

    private fun openCriticsReview(): View.OnClickListener = View.OnClickListener {
        val openLinkIntent = Intent(Intent.ACTION_VIEW)
        openLinkIntent.data = Uri.parse(review.url)
        startActivity(openLinkIntent)
    }

    override fun onCreateOptionsMenu(
        menu: Menu,
        inflater: MenuInflater
    ) {
        inflater.inflate(R.menu.menu_review_details, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val iconFavorite = menu.findItem(R.id.menu_item_favorite_critics_false_details)

        GlobalScope.launch {
            if (isFavorite()){
                iconFavorite.setIcon(R.drawable.ic_mark_favorite_true)
            } else {
                iconFavorite.setIcon(R.drawable.ic_mark_favorite_false)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.menu_item_share_link_details) {
            val sharedIntent = Intent(Intent.ACTION_SEND)
            sharedIntent.type = "text/plain"
            sharedIntent.putExtra(Intent.EXTRA_SUBJECT, "Movie Reviews")
            val message = "${review.linkDescription}. \n\n ${review.url}"
            sharedIntent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(Intent.createChooser(sharedIntent, "Share Critics"))
        }

        if (id == R.id.menu_item_favorite_critics_false_details) {
            GlobalScope.launch {
                if (!isFavorite()) {
                    DialogAddFavorite(review).show(childFragmentManager, "Confirm Review Favorite")
                } else {
                    DialogRemoveFavorite(review).show(childFragmentManager, "Critics Review Remove")
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private suspend fun isFavorite(): Boolean =
        homeViewModel.isFavorite(review)
}
