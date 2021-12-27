package com.example.myapplicationone.dataClass

import androidx.room.*
import androidx.room.RoomDatabase

import androidx.room.Database





@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun getAll() : List<Book?>?

    @Query("SELECT * FROM book WHERE id = :id")
    fun getBuId(id: String) : Book


    @Insert
    fun insert(book: Book?)

    @Update
    fun update(book: Book?)

    @Delete
    fun delete(book: Book?)

}

@Database(entities = [Book::class], version = 2)
@TypeConverters(authorsConverter::class, imagesConverter::class)
//@TypeConverters(imagesConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao?
}

//@Query("SELECT * FROM employee")
//fun getAll(): List<Employee?>?
//
//@Query("SELECT * FROM employee WHERE id = :id")
//fun getById(id: Long): Employee?
//
//@Insert
//fun insert(employee: Employee?)
//
//@Update
//fun update(employee: Employee?)
//
//@Delete
//fun delete(employee: Employee?)