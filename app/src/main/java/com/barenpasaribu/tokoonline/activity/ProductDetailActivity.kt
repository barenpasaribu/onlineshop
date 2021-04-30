package com.barenpasaribu.tokoonline.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.barenpasaribu.tokoonline.R
import com.barenpasaribu.tokoonline.model.Product
import com.barenpasaribu.tokoonline.helper.Utils
import com.barenpasaribu.tokoonline.room.MyDatabase
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class ProductDetailActivity : AppCompatActivity() {

    lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        getInfo()
        mainButton()
    }

    //insert data to room
    private fun checkCart(){

    }
    private fun insert() {
        val database: MyDatabase = MyDatabase.getInstance(this)!!
        CompositeDisposable().add(Observable.fromCallable {
            database.daoCart().insert(product)
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if(database.daoCart().getAll().isEmpty()) {
                    //TODO
                }
                Log.d("response", "data inserted")
            }
        )
    }

    private fun mainButton() {
        btn_cart.setOnClickListener {
            insert()
        }
        btn_favorite.setOnClickListener {

            val database: MyDatabase = MyDatabase.getInstance(this)!! //get connection instance
            val listData = database.daoCart().getAll()
            for (product: Product in listData) {
                println("---------")
                println(product.name)
                println(product.price)
            }
        }
    }

    private fun getInfo() {
        //get data
        val data = intent.getStringExtra("product_detail")
        product = Gson().fromJson<Product>(data, Product::class.java)

        //set value
        tv_name_product.text = product.name
        tv_product_price.text = Utils.rupiah(product.price.toDouble())
        tv_description.text = product.description

        //set image use library picasso
        val urlImage = "http://10.0.2.2:8000/storage/product/" + product.image
        Picasso.get()
            .load(urlImage)
            .placeholder(R.drawable.product)
            .error(R.drawable.product)
            .resize(200, 200)
            .into(image)

        //set toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = product.name
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}