package com.example.settingsexe01

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.util.Log

/**
 * Created by Edward on 1/15/2019.
 *
 * customized PreferenceFragmentCompat
 */
class SettingsFragment: PreferenceFragmentCompat() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate() called. ")
        super.onCreate(savedInstanceState)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        Log.d(TAG, "onCreatePreferences() called. ")
        addPreferencesFromResource(R.xml.app_preferences)
    }

    override fun onDisplayPreferenceDialog(preference: Preference?) {
        Log.d(TAG, "onDisplayPreferenceDialog() called. ")
        // Try if the preference is one of our custom Preferences
        var dialogFragment: DialogFragment? = null
        if (preference is TimePreference) {
            // Create a new instance of TimePreferenceDialogFragment with the key of the related Preference
            dialogFragment = TimePreferenceDialogFragmentCompat.newInstance(preference.getKey())
        }

        // If it was one of our cutom Preferences, show its dialog
        if (dialogFragment != null) {
            dialogFragment.setTargetFragment(this, 0)
            dialogFragment.show(
                this.fragmentManager,
                "android.support.v7.preference" + ".PreferenceFragment.DIALOG"
            )
        } else { // Could not be handled here. Try with the super method.
            super.onDisplayPreferenceDialog(preference)
        }
    }

    companion object {
        private const val TAG = "SettingsFragment"
    }
}