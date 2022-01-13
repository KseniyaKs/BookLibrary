package com.example.myapplicationone.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationone.App
import com.example.myapplicationone.BookAdapter
import com.example.myapplicationone.R
import com.example.myapplicationone.appComponent
import com.example.myapplicationone.dataclass.AppDatabase
import com.example.myapplicationone.dataclass.Book
import com.example.myapplicationone.dataclass.BookDao
import com.example.myapplicationone.dataclass.BookMapper
import kotlinx.coroutines.*
import javax.inject.Inject

class LikeFragment : Fragment() {

    @Inject
    lateinit var db: AppDatabase

    private val adapter = BookAdapter(oneClickListener = object : BookAdapter.OnItemClickListener {
        override fun onItemClick(book: Book?) {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .add(R.id.mainContainer, SecondFragment.newInstance(book), "asdasd")
                .addToBackStack("SECOND_FR")
                .commit()

        }
    }, imgClickListener = object : BookAdapter.OnItemClickListener {
        override fun onItemClick(book: Book?) {
            requireActivity().supportFragmentManager
            val imageFragment = ImageFragment.newInstance(book)
            imageFragment.show(childFragmentManager, "IMAGE_FR")

        }
    }, doubleClickListener = object : BookAdapter.OnItemClickListener {
        @SuppressLint("Range")
        override fun onItemClick(book: Book?) {
            Toast.makeText(requireContext(),"This book was delete", Toast.LENGTH_SHORT).show()

            val bookDao: BookDao? = db.bookDao()
            val entity = book?.let { BookMapper.mapFromBookToBookEntity(it) }

            if (bookDao?.getBuId(book!!.id)?.isLike == true) {
//                view?.setBackgroundColor(Color.parseColor("#808080"))
                bookDao.delete(entity)
            }

        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireContext().appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_like, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtEmptyLikeList = view.findViewById<TextView>(R.id.emptyLikeList)
        val rclView = view.findViewById<RecyclerView>(R.id.rclView)

        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch(Dispatchers.Main.immediate) {
            Log.d("ASDASD", (Looper.myLooper() == Looper.getMainLooper()).toString())
            val likeListEntity = db.bookDao()?.getAll()
            val likeList = mutableListOf<Book>()

            if (likeListEntity != null) {
                for (bookEntity in likeListEntity) {
                    likeList.add(BookMapper.mapFromBookEntityToBook(bookEntity))
                }
            }
            Log.d("ASDASD2", (Looper.myLooper() == Looper.getMainLooper()).toString())

                if (likeList.isEmpty()) txtEmptyLikeList.visibility = TextView.VISIBLE
                else txtEmptyLikeList.visibility = TextView.GONE

                adapter.setItems(likeList as List<Book>)
        }
        rclView?.layoutManager = LinearLayoutManager(requireContext())
        rclView?.adapter = adapter


    }

    companion object {

        fun newInstance() = LikeFragment()
    }
}