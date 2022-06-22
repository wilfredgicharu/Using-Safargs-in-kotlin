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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.raywenderlich.android.safeargs.R
import com.raywenderlich.android.safeargs.TravelAddOnsProvider
import com.raywenderlich.android.safeargs.TravelerInformation
import com.raywenderlich.android.safeargs.databinding.FragmentTravelInformationBinding

/**
 * Screen that allows the user to input their information, select travel add-ons and optionally
 * enter a promo code. User input validation isn't performed, this can be an enhancement.
 */
class TravelInformationFragment : Fragment() {

  private lateinit var binding: FragmentTravelInformationBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentTravelInformationBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    // Set up add-ons list
    val clickListener = TravelAddOnsClickListener()
    setUpAddOns(clickListener)

    // When the user clicks on next, get the information they entered, and pass it to the confirmation screen
    binding.next.setOnClickListener {

      val travelerInformation = getTravelerInformation()
      val addOns = getAddOns(clickListener)
      val promoCode = getPromoCode()

      val directions = TravelInformationFragmentDirections.actionTravelInformationFragmentToConfirmationFragment(promoCode, addOns, travelerInformation)




      findNavController().navigate(directions)
    }
  }

  private fun setUpAddOns(clickListener: TravelAddOnsClickListener) {
    val addOns = TravelAddOnsProvider.get()
    with(binding.layoutTravelAddOns.travelAddOns) {
      adapter = TravelAddOnsAdapter(addOns, clickListener)
      layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.HORIZONTAL, false)
      setHasFixedSize(true)
    }
  }

  private fun getTravelerInformation(): TravelerInformation {
    return TravelerInformation(
        fullName = binding.layoutTravelInformation.fullNameEditText.text.toString(),
        age = try {
          binding.layoutTravelInformation.ageEditText.text.toString().toInt()
        } catch (exception: NumberFormatException) {
          0
        },
        passportNumber = binding.layoutTravelInformation.passportNumberEditText.text.toString()
    )
  }

  private fun getAddOns(clickListener: TravelAddOnsClickListener): IntArray {
    return clickListener.get().map { addOn -> addOn.id }.toIntArray()
  }

  private fun getPromoCode(): String {
    return binding.layoutPromoCode.promoCodeEditText.text.toString()
  }
}