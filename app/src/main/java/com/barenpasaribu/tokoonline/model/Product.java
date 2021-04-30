package com.barenpasaribu.tokoonline.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "cart")
public class Product implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idCart")
    public int idCart;

    public int id;
    public String name;
    public String price;
    public String description;
    public int qty;
    public int category_id;
    public String image;
    public String created_at;
    public String updated_at;
}
