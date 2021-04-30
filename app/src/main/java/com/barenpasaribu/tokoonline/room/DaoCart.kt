package com.barenpasaribu.tokoonline.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.barenpasaribu.tokoonline.model.Product

@Dao
interface DaoCart {
    @Insert(onConflict = REPLACE)
    fun insert(data: Product)

    @Delete
    fun delete(data: Product)

    @Update
    fun update(data: Product): Int

    @Query("SELECT * FROM cart ORDER BY id ASC")
    fun getAll(): List<Product>

    @Query("SELECT * FROM cart WHERE id = :id LIMIT 1")
    fun getCartById(id: Int): Product

    @Query("DELETE FROM cart")
    fun deleteAll(): Int
}