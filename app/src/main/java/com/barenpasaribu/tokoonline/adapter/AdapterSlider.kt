package com.barenpasaribu.tokoonline.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.viewpager.widget.PagerAdapter
import com.barenpasaribu.tokoonline.R
import kotlinx.android.synthetic.main.item_slider.view.*

class AdapterSlider(private var data: ArrayList<Int>, var context: Activity?) : PagerAdapter() {

  private lateinit var layoutInflater: LayoutInflater

  override fun isViewFromObject(view: View, `object`: Any): Boolean {
    return view == `object`
  }

  override fun getCount(): Int {
    return data.size
  }

  @NonNull
  override fun instantiateItem(@NonNull container: ViewGroup, position: Int): Any {
    layoutInflater = LayoutInflater.from(context)
    val view: View = layoutInflater.inflate(R.layout.item_slider, container, false)
    view.iv_image_slider.setImageResource(data[position])

    container.addView(view, 0)
    return view
  }

  override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    // super.destroyItem(container, position, `object`)
    container.removeView(`object` as View)
  }
}