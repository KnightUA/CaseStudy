package ua.shtest.casestudy.utils

import android.content.Context
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

object EditTextExtensions {

    fun EditText.showKeyboard() {
        if (!isFocused) requestFocus()

        val inputMethodManager: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    fun EditText.hideKeyboard() = hideKeyboard(InputMethodManager.HIDE_IMPLICIT_ONLY)

    private fun EditText.hideKeyboard(flag: Int) {
        if (isFocused) clearFocus()

        val inputMethodManager: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.windowToken, flag)
    }

    fun EditText.doOnActionDone(block: () -> Unit) =
        this.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == IME_ACTION_DONE) {
                block()
                true
            } else {
                false
            }
        }
}