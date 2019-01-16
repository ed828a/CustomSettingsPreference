package com.example.settingsexe01

import android.os.Bundle
import android.support.v7.preference.PreferenceDialogFragmentCompat
import android.text.format.DateFormat
import android.view.View
import android.widget.TimePicker

/**
 * Created by Edward on 1/16/2019.
 *
 *  customized PreferenceDialogFragmentCompat
 */
class TimePreferenceDialogFragmentCompat : PreferenceDialogFragmentCompat() {

    private var mTimePicker: TimePicker? = null

    // populate time from SharedPreference to TimerPicker(View)
    override fun onBindDialogView(view: View) {
        super.onBindDialogView(view)

        mTimePicker = view.findViewById(R.id.edit) as TimePicker

        // Exception when there is no TimePicker
        if (mTimePicker == null) {
            throw IllegalStateException("Dialog view must contain" + " a TimePicker with id 'edit'")
        }

        // Get the time from the related Preference
        var minutesAfterMidnight: Int? = null
        val preference = preference
        if (preference is TimePreference) {
            minutesAfterMidnight = preference.time
        }

        // Set the time to the TimePicker
        if (minutesAfterMidnight != null) {
            val hours = minutesAfterMidnight / 60
            val minutes = minutesAfterMidnight % 60
            val is24hour = DateFormat.is24HourFormat(context)

            mTimePicker!!.setIs24HourView(is24hour)
            mTimePicker!!.hour = hours
            mTimePicker!!.minute = minutes
        }
    }

    override fun onDialogClosed(positiveResult: Boolean) {
        if (positiveResult) {
            // generate value to save
            val hours = mTimePicker!!.hour
            val minutes = mTimePicker!!.minute
            val minutesAfterMidnight = hours * 60 + minutes

            // Get the related Preference and save the value
            val preference = preference
            if (preference is TimePreference) {
                // This allows the client to ignore the user value.
                if (preference.callChangeListener(minutesAfterMidnight)) {
                    // Save the value
                    preference.time = minutesAfterMidnight
                }
            }
        }
    }

    companion object {

        fun newInstance(key: String): TimePreferenceDialogFragmentCompat {
            val fragment = TimePreferenceDialogFragmentCompat()
            val b = Bundle(1)
            b.putString(PreferenceDialogFragmentCompat.ARG_KEY, key)
            fragment.arguments = b

            return fragment
        }
    }
}
