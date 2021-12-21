package com.example.myapplicationone.Fragment

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationone.*
import com.example.myapplicationone.dataClass.Book
import com.example.myapplicationone.dataClass.ListBook
import com.example.myapplicationone.ViewModel.MainViewModel


import androidx.room.Room
import com.example.myapplicationone.App

import com.example.myapplicationone.dataClass.AppDatabase





class MainFragment : Fragment() {

    private val model: MainViewModel by viewModels { defaultViewModelProviderFactory }

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
//            Log.d("TAG","DOUBLE-CLICK-IN-ADAPTER")
        }
    }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView = view.findViewById<SearchView>(R.id.searchView)
        val rclView = view?.findViewById<RecyclerView>(R.id.recyclerView)

        searchView.setOnClickListener { searchView.isIconified = false }
        //        searchView.setOnQueryTextListener(SearchView.OnQueryTextListener)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("TAG", "onQueryTextSubmit")
                model.loadData(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("TAG", "onQueryTextChange")
                return true
            }
        })

//        Log.d("TAG", model.toString())
        val data: LiveData<ListBook> = model.data
        data.observe(viewLifecycleOwner, Observer<ListBook>() {
            adapter.setItems(it.list)
        })

        rclView?.layoutManager = LinearLayoutManager(requireContext())
        rclView?.adapter = adapter
    }

    //    var db = Room.databaseBuilder(
//        ApplicationProvider.getApplicationContext<Context>(),
//        AppDatabase::class.java, "database"
//    ).build()
    var dataBase = Room.databaseBuilder(
        context!!.applicationContext,
        AppDatabase::class.java,
        "database"
    ).build()


    override fun onDetach() {
        super.onDetach()
        Log.d("TAG", "DETACH")
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}