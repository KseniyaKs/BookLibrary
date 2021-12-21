package com.example.myapplicationone.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.myapplicationone.dataClass.Book
import com.example.myapplicationone.dataClass.BookDetails
import com.example.myapplicationone.R
import com.example.myapplicationone.ViewModel.SecondViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {

    private val model: SecondViewModel by viewModels { defaultViewModelProviderFactory }
//    val imgView = view?.findViewById<ImageView>(R.id.imgOfBook)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG","onCreate_SecondFr")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TAG","onCreateView_SecondFr")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var book: Book = arguments?.get(BOOK_KEY) as Book

        if (savedInstanceState == null) {
            model.loadData(book)
        }

        val tittleTxt = view.findViewById<TextView>(R.id.tittleTxt)
        val imgView = view.findViewById<ImageView>(R.id.imgOfBook)
        val detailsTxt = view.findViewById<TextView>(R.id.detailsTxt)
        val dateTxt = view.findViewById<TextView>(R.id.dateTxt)
        val webTxt = view.findViewById<TextView>(R.id.webTxt)

        val exit = view.findViewById<ImageView>(R.id.backBtn)
        exit.setOnClickListener {
            activity?.onBackPressed()
        }

        Glide.with(imgView.context)
            .load(book.volumeInfo.imgLinks?.smallImg?.replace("http", "https")).into(imgView)

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        progressBar.visibility = ProgressBar.VISIBLE

        Log.d("TAG", "onViewCreatedSecondFr")

        val data: LiveData<BookDetails> = model.data
        data.observe(viewLifecycleOwner,Observer<BookDetails>() {
            if (it.volumeInfo.description == null) {
                detailsTxt.text == null
            } else
                detailsTxt.text = it.volumeInfo.description?.replace("\"", "")

            tittleTxt.text = it.volumeInfo.title
            dateTxt.text = it.volumeInfo.date?.replace("\"", "")
            webTxt.text = it.volumeInfo.previewLink?.replace("\"", "")
            progressBar.visibility = ProgressBar.GONE
        })
    }

    companion object {
        private const val BOOK_KEY = "KEY"

        @JvmStatic
        fun newInstance(book: Book?) = SecondFragment().apply {
            arguments = Bundle().apply {
                putSerializable(BOOK_KEY, book)
            }
        }
    }
}