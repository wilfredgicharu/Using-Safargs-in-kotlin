/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.safeargs.travel_information

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.android.safeargs.TravelAddOn
import com.raywenderlich.android.safeargs.databinding.ListItemTravelAddOnBinding

/**
 * [RecyclerView.Adapter] for travel add-ons that displays a list of add-ons and allows the user
 * to select none, one or many.
 */
class TravelAddOnsAdapter(private val addOns: List<TravelAddOn>, private val clickListener: TravelAddOnsClickListener) : RecyclerView.Adapter<TravelAddOnsAdapter.TravelAddOnViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelAddOnViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding = ListItemTravelAddOnBinding.inflate(inflater, parent, false)
    return TravelAddOnViewHolder(binding)
  }

  override fun onBindViewHolder(holder: TravelAddOnViewHolder, position: Int) {
    holder.bind(addOns[position], clickListener)
  }

  override fun getItemCount() = addOns.size

  class TravelAddOnViewHolder(private val binding: ListItemTravelAddOnBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(addOn: TravelAddOn, clickListener: TravelAddOnsClickListener) {
      binding.addOnLabel.text = addOn.label
      binding.addOnIcon.setImageDrawable(ContextCompat.getDrawable(itemView.context, addOn.icon))
      itemView.setOnClickListener {
        val visibility = binding.addOnCheckedOverlay.visibility
        if (visibility == View.VISIBLE) {
          binding.addOnCheckedOverlay.visibility = View.GONE
          clickListener.remove(addOn)
        } else {
          binding.addOnCheckedOverlay.visibility = View.VISIBLE
          clickListener.add(addOn)
        }
      }
    }
  }
}