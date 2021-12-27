package com.example.myapplicationone

import android.view.*
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationone.dataClass.Book


class BookAdapter(
    val oneClickListener: OnItemClickListener,
    val doubleClickListener: OnItemClickListener,
    val imgClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val bookList: MutableList<Book> = mutableListOf()


    interface OnItemClickListener {
        fun onItemClick(book: Book?)
    }

    fun setItems(newBookList: List<Book>) {
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.book_view_holder, parent, false)
        val bookViewHolder = BookViewHolder(view)

        bookViewHolder.itemView.setOnTouchListener(object : View.OnTouchListener {
            val gesDetector =
                GestureDetector(view.context, object : GestureDetector.SimpleOnGestureListener() {
                    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                        oneClickListener.onItemClick(bookViewHolder.book)
                        return super.onSingleTapConfirmed(e)
                    }

                    override fun onDoubleTap(e: MotionEvent?): Boolean {

//                        Toast.makeText(view.context, "onDoubleTap", Toast.LENGTH_SHORT).show()

                        bookViewHolder.book!!.isLike = !bookViewHolder.book!!.isLike
                        if (bookViewHolder.book!!.isLike) {
                            bookViewHolder.imgLike.visibility = ImageView.VISIBLE
                        } else {
                            bookViewHolder.imgLike.visibility = ImageView.GONE
                        }
                        doubleClickListener.onItemClick(bookViewHolder.book)
                        return super.onDoubleTap(e)
                    }
                })

            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                gesDetector.onTouchEvent(event)
                return true
            }
        })


        bookViewHolder.imgView.setOnClickListener {
            bookViewHolder.book?.let { it1 -> imgClickListener.onItemClick(it1) }
        }

        return bookViewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val book = bookList[position]
        (holder as BookViewHolder).tittleTxt.text = book.volumeInfo.title
        holder.authorTxt.text = book.volumeInfo.author?.firstOrNull()
            ?.replace("[", "")
            ?.replace("]", "")
            ?.replace("\"", "")
        holder.showImage(book)

        if (book.isLike) holder.imgLike.visibility = ImageView.VISIBLE
        else holder.imgLike.visibility = ImageView.GONE
//        if (!bookDao?.getBuId(book!!.id)!!.isLike) bookDao?.delete(book)
//        else {
//            bookDao?.insert(book)
//        }

    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}
