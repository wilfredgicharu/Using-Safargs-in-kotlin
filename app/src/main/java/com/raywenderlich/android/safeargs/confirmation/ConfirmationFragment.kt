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

package com.raywenderlich.android.safeargs.confirmation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.raywenderlich.android.safeargs.R
import com.raywenderlich.android.safeargs.TravelAddOnsProvider
import com.raywenderlich.android.safeargs.TravelerInformation
import com.raywenderlich.android.safeargs.databinding.FragmentConfirmationBinding

/**
 * Screen that displays the user's travel information in order for them to confirm it.
 */
class ConfirmationFragment : Fragment() {

  private lateinit var binding: FragmentConfirmationBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentConfirmationBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    // Retrieve passed arguments and display them

    val bundle = arguments
    if (bundle == null){
      Log.e("confirmation", "Confirmation did not receive traveller information")
      return
    }

    val args = ConfirmationFragmentArgs.fromBundle(bundle)
    showTravelerInformation(args.travelerInformation)
    showPromoCode(args.promoCode)
    showTravelAddOns(args.travelAddOns)


    // TODO: Get travel information from previous screen

    // Set up confirmation button click listener
    binding.confirmTravelInformationButton.setOnClickListener {
      Toast.makeText(requireContext(), R.string.travel_information_confirmation_message, Toast.LENGTH_SHORT).show()
    }
  }

  private fun showTravelerInformation(travelerInformation: TravelerInformation) {
    binding.fullNameTextView.text = getString(R.string.traveler_information_full_name, travelerInformation.fullName)
    binding.ageTextView.text = getString(R.string.traveler_information_age, travelerInformation.age)
    binding.passportNumberTextView.text = getString(R.string.traveler_information_passport_number, travelerInformation.passportNumber)
  }

  private fun showTravelAddOns(travelAddOns: IntArray) {
    if (travelAddOns.isEmpty()) {
      binding.travelAddOnsTextView.text = getString(R.string.travel_add_ons_none)
      return
    }

    val addOns = StringBuilder()
    for (i in travelAddOns.indices) {
      val addOn = TravelAddOnsProvider.get(travelAddOns[i])
      addOns.append(addOn.label)
      if (i != travelAddOns.lastIndex) {
        addOns.append(", ")
      }
    }
    binding.travelAddOnsTextView.text = addOns.toString()
  }

  private fun showPromoCode(promoCode: String?) {
    if (promoCode.isNullOrBlank()) {
      binding.promoCodeTextView.text = getString(R.string.promo_code_none)
    } else {
      binding.promoCodeTextView.text = promoCode
    }
  }
}