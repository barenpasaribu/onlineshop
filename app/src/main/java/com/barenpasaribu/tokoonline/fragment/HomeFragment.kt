package com.barenpasaribu.tokoonline.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.barenpasaribu.tokoonline.R
import com.barenpasaribu.tokoonline.adapter.AdapterProduct
import com.barenpasaribu.tokoonline.adapter.AdapterSlider
import com.barenpasaribu.tokoonline.app.ApiConfig
import com.barenpasaribu.tokoonline.model.Product
import com.barenpasaribu.tokoonline.model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var rvProductLatest: RecyclerView
    private lateinit var rvProductBest: RecyclerView
    private lateinit var rvProductElectronic: RecyclerView
    private lateinit var vpSlider: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        init(view) //init array slider
        getProduct()
        return view
    }

    private fun init(view: View) {

        rvProductBest = view.findViewById(R.id.rv_best_seller)
        rvProductLatest = view.findViewById(R.id.rv_latest)
        rvProductElectronic = view.findViewById(R.id.rv_electronic)
        vpSlider = view.findViewById(R.id.vp_slider)
    }

    private var listProduct: ArrayList<Product> = ArrayList()
    private fun getProduct() {
        ApiConfig.instanceRetrofit.getProduct().enqueue(object : Callback<ResponseModel> {
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
            }

            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val responData = response.body()!!
                Log.d("test", "data $responData")
                if (responData.success) {
                    listProduct = responData.products
                    displayProduct()
                }
            }

        })
    }

    private fun displayProduct() {
        val arraySlider = ArrayList<Int>()
        arraySlider.add(R.drawable.slider1)
        arraySlider.add(R.drawable.slider2)
        arraySlider.add(R.drawable.slider3)

        val adapterSlider = AdapterSlider(arraySlider, activity)
        vpSlider.adapter = adapterSlider

        // layout as horizontal product latest
        val linearLayoutManagerLatest = LinearLayoutManager(activity)
        linearLayoutManagerLatest.orientation = LinearLayoutManager.HORIZONTAL

        // layout as horizontal product best seller
        val linearLayoutManagerBest = LinearLayoutManager(activity)
        linearLayoutManagerBest.orientation = LinearLayoutManager.HORIZONTAL

        // layout as horizontal product electronic
        val linearLayoutManagerElectronic = LinearLayoutManager(activity)
        linearLayoutManagerElectronic.orientation = LinearLayoutManager.HORIZONTAL

        // add array data product best to layout
        rvProductBest.adapter = AdapterProduct(requireActivity(), listProduct)
        rvProductBest.layoutManager = linearLayoutManagerBest

        // add array data latest product to layout
        rvProductLatest.adapter = AdapterProduct(requireActivity(), listProduct)
        rvProductLatest.layoutManager = linearLayoutManagerLatest

        // add array data product electronic to layout
        rvProductElectronic.adapter = AdapterProduct(requireActivity(), listProduct)
        rvProductElectronic.layoutManager = linearLayoutManagerElectronic
    }
}