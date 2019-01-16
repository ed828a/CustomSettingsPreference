package com.example.settingsexe01

import android.content.Context
import android.content.res.TypedArray
import android.support.v7.preference.DialogPreference
import android.util.AttributeSet

/**
 * Created by Edward on 1/15/2019.
 *
 *  customized DialogPreference
 */
class TimePreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.preferenceStyle,   // Use the preferenceStyle as the default style
    defStyleRes: Int = defStyleAttr
) : DialogPreference(context, attrs, defStyleAttr, defStyleRes) {

    var time: Int = 0
        set(time) {
            field = time
            // save to SharedPreference
            persistInt(time)
        }
    private val mDialogLayoutResId = R.layout.pref_dialog_time

    override fun onGetDefaultValue(array: TypedArray, index: Int): Any {
        // The type of this preference is Int, so we read the default value from the attributes
        // as Int. Fallback value is set to 0.
        return array.getInt(index, 0)
    }

    override fun onSetInitialValue(restorePersistedValue: Boolean, defaultValue: Any?) {
        // Read the value. Use the default value if it is not possible.
        time = if (restorePersistedValue) getPersistedInt(time) else defaultValue as Int
    }

    override fun getDialogLayoutResource(): Int {
        return mDialogLayoutResId
    }
}
