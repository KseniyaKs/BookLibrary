package com.example.myapplicationone.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.myapplicationone.dataclass.Book
import com.example.myapplicationone.dataclass.BookDetails
import com.example.myapplicationone.R
import com.example.myapplicationone.ViewModel.ImageViewModel

class ImageFragment : DialogFragment() {
    private val model: ImageViewModel by viewModels { defaultViewModelProviderFactory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val book: Book = arguments?.get(BOOK_DETAILS_KEY) as Book

        if (savedInstanceState == null) {
            model.loadData(book)
        }

        val amount = view.findViewById<TextView>(R.id.amount)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        progressBar.visibility = ProgressBar.VISIBLE


        val data: LiveData<BookDetails> = model.data
        data.observe(viewLifecycleOwner, {
            if (it.saleInfo.listPrice?.amount.toString() == "null") amount.text =
                getString(R.string.havent_price)
            else amount.text = getString(R.string.price) + it.saleInfo.listPrice?.amount.toString() + "RUB"
            progressBar.visibility = ProgressBar.GONE

            Log.d("TAG", "onViewCreatedIMAGE")
        })
    }

    companion object {
        private const val BOOK_DETAILS_KEY = "DETAILS_KEY"

        @JvmStatic
        fun newInstance(book: Book?) = ImageFragment().apply {
            arguments = Bundle().apply {
                putSerializable(BOOK_DETAILS_KEY, book)
            }
        }
    }
}