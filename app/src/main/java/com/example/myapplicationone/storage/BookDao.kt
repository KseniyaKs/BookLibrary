package com.example.myapplicationone.dataclass

import androidx.room.*
import androidx.room.RoomDatabase

import androidx.room.Database
import com.example.myapplicationone.dataclass.entity.BookEntity


@Dao
interface BookDao {
    @Query("SELECT * FROM bookentity")
    suspend fun getAll() : List<BookEntity>?

    @Query("SELECT * FROM bookentity WHERE id = :id")
    fun getBuId(id: String) : BookEntity


    @Insert
    fun insert(book: BookEntity?)

    @Update
    fun update(book: BookEntity?)

    @Delete
    fun delete(book: BookEntity?)

}

@Database(entities = [BookEntity::class], version = 2)
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