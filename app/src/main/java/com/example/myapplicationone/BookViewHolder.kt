package com.example.myapplicationone

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationone.dataclass.Book

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
    var book: Book? = null

    val imgLike: ImageView = itemView.findViewById(R.id.like)

    val tittleTxt: TextView = itemView.findViewById<TextView>(R.id.tittleTxt)
    val authorTxt: TextView = itemView.findViewById<TextView>(R.id.authorTxt)

    val imgView: ImageView = itemView.findViewById<ImageView>(R.id.imgOfBook)

    val lnrLayout: LinearLayout = itemView.findViewById<LinearLayout>(R.id.linearLayout)


    fun showImage (bookItem: Book) {
        this.book = bookItem
        Glide.with(imgView.context).load(bookItem.volumeInfo.imgLinks?.smallImg?.replace("http", "https")).into(imgView)
    }
}

