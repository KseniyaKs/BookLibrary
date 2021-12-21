package com.example.myapplicationone.Fragment

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
import com.example.myapplicationone.dataClass.Book
import com.example.myapplicationone.dataClass.BookDetails
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

        var book: Book = arguments?.get(BOOK_DETAILS_KEY) as Book

        if (savedInstanceState == null) {
            model.loadData(book)
        }

        val amount = view.findViewById<TextView>(R.id.amount)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)


//        val retrofit: Retrofit = Retrofit.Builder()
//            .baseUrl("https://www.googleapis.com/")
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()

        progressBar.visibility = ProgressBar.VISIBLE


        val data: LiveData<BookDetails> = model.data
        data.observe(viewLifecycleOwner, Observer<BookDetails>() {
            if (it.saleInfo.listPrice?.amount.toString() == "null") amount.text =
                "Have not this price"
            else amount.text = "Price: " + it.saleInfo.listPrice?.amount.toString() + "RUB"
            progressBar.visibility = ProgressBar.GONE

            Log.d("TAG", "onViewCreatedIMAGE")
        })

//        val service: APIService = retrofit.create(APIService::class.java)

//        service.getBook(book.id)
//            .enqueue(object : Callback<BookDetails>{
//                override fun onResponse(call: Call<BookDetails>, response: Response<BookDetails>) {
//                    Log.d("ppp", "pppp")
//                    response.body()?.let {
//                        listOf(it.saleInfo)
//
//                        if (it.saleInfo.listPrice?.amount.toString() == "null") {
//                            amount.text = "Have not this price"
//                        }
//                        else amount.text = "Price: " + it.saleInfo.listPrice?.amount.toString() + " RUB"
//
//                        progressBar.visibility = ProgressBar.GONE
//
//                    }
//                }

//                override fun onFailure(call: Call<BookDetails>, t: Throwable) {
//                    Log.d("Tag", "image_fragment_onFailure")
//                }
//
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