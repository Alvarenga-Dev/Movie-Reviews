package com.alvarengadev.moviereviews.view.details.dialog.remove

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.alvarengadev.moviereviews.R
import com.alvarengadev.moviereviews.data.domain.Review
import com.alvarengadev.moviereviews.view.home.HomeViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DialogRemoveFavorite(
    private val review: Review
) : AppCompatDialogFragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(this.activity)
        val layoutInflater = activity?.layoutInflater
        val view = layoutInflater?.inflate(R.layout.dialog_remove_favorite, null)!!
        builder.setView(view)
        buttons(view)

        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    private fun buttons(view: View) {
        val btnCancel = view.findViewById(R.id.btn_cancel_dialog_remove) as Button
        val btnRemoveFavorite = view.findViewById(R.id.btn_remove_favorite_dialog_remove) as Button

        btnCancel.setOnClickListener{
            dismiss()
        }

        btnRemoveFavorite.setOnClickListener {
            GlobalScope.launch {
                homeViewModel.removeReviewsFavorites(review)
                dismiss()
            }
        }
    }
}