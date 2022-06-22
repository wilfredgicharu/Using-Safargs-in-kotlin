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

package com.raywenderlich.android.safeargs

/**
 * Provides a list of manually generated [TravelAddOn]. In a real app, this information can instead
 * be fetched from a server or from local storage if it's static information.
 */
object TravelAddOnsProvider {

  private val addOns = listOf(
      TravelAddOn(0, "Comfort Seat", 30F, R.drawable.ic_comfort_seat),
      TravelAddOn(1, "On-board Shopping", 10F, R.drawable.ic_onboard_shopping),
      TravelAddOn(2, "Internet Connection", 30F, R.drawable.ic_internet_connection),
      TravelAddOn(3, "Cancellation Cover", 40F, R.drawable.ic_cancellation_cover),
      TravelAddOn(4, "Lounge", 50F, R.drawable.ic_lounge),
  )

  fun get(): List<TravelAddOn> {
    return addOns
  }

  fun get(addOnId: Int): TravelAddOn {
    return addOns.firstOrNull { addOn -> addOn.id == addOnId }
        ?: throw IllegalArgumentException("Invalid add-on id $addOnId")
  }
}