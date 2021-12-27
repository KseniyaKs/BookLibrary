package com.example.myapplicationone.Fragment

import android.os.Bundle
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
import com.example.myapplicationone.dataClass.AppDatabase
import com.example.myapplicationone.dataClass.Book
import com.example.myapplicationone.dataClass.BookDao

class LikeFragment : Fragment() {

    private val db: AppDatabase by lazy { (requireContext().applicationContext as App).getDatabase() }

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
            imageFragment.show(requireFragmentManager(), "IMAGE_FR")

        }
    }, doubleClickListener = object : BookAdapter.OnItemClickListener {
        override fun onItemClick(book: Book?) {
            Toast.makeText(requireContext(),"This book was delete", Toast.LENGTH_SHORT).show()


            val bookDao: BookDao? = db?.bookDao()
            if (bookDao?.getBuId(book!!.id)?.isLike == true) bookDao.delete(book)

        }
    })

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
        val rclView = view?.findViewById<RecyclerView>(R.id.rclView)
        val likeList = db.bookDao()?.getAll()

        if (likeList!!.isEmpty()) {
            txtEmptyLikeList.visibility = TextView.VISIBLE
//            Toast.makeText(requireContext(),"LikeList is empty",Toast.LENGTH_SHORT).show()
        }
        else txtEmptyLikeList.visibility = TextView.GONE

        rclView?.layoutManager = LinearLayoutManager(requireContext())
        rclView?.adapter = adapter

        adapter.setItems(likeList as List<Book>)
    }

    companion object {

        fun newInstance() = LikeFragment()
    }
}