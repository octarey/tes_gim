package com.example.test_global_indo_multimedia.models

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "cart")
data class Cart (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "price")
    val price: Float,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "rate")
    val rate: Float,
    @ColumnInfo(name = "count")
    val count:Int = 0
)

@Dao
interface CartDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCart(db : Cart)

    @Update
    fun updateCart(db: Cart)

    @Delete
    fun deleteCart(db: Cart)

    @Query("SELECT * FROM cart")
    fun getCart(): LiveData<List<Cart>>
}