package io.github.koalaopensource.koala.ui

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.IntDef
import androidx.annotation.IntRange
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

/** An Activity with helper properties and methods. */
abstract class BaseActivity : AppCompatActivity() {

	/**
	 * Displays a snackbar message to the user.
	 *
	 * @param[message] The string message to be displayed to the user
	 * @param[duration] How long to display the message. Can be [Snackbar.LENGTH_SHORT], [Snackbar.LENGTH_LONG],
	 *                  [Snackbar.LENGTH_INDEFINITE], or a custom duration in milliseconds.
	 */
	fun showSnackbarMessage(
		message: String,
		@SnackbarDuration duration: Int = Snackbar.LENGTH_SHORT
	) {
		Snackbar.make(window.decorView.rootView, message, duration).show()
	}

	/**
	 * Displays a snackbar message to the user.
	 *
	 * @param[resId] The string resource id of the message to be displayed to the user
	 * @param[duration] How long to display the message. Can be [Snackbar.LENGTH_SHORT], [Snackbar.LENGTH_LONG],
	 *                  [Snackbar.LENGTH_INDEFINITE], or a custom duration in milliseconds.
	 */
	fun showSnackbarMessage(
		@StringRes resId: Int,
		@SnackbarDuration duration: Int = Snackbar.LENGTH_SHORT
	) {
		Snackbar.make(window.decorView.rootView, resId, duration).show()
	}

	/**
	 * Displays a toast message to the user.
	 *
	 * @param[message] The string message to be displayed to the user
	 * @param[duration] How long to display the message.  Either [Toast.LENGTH_SHORT] or [Toast.LENGTH_LONG]
	 */
	fun showToastMessage(message: String, @ToastDuration duration: Int = Toast.LENGTH_SHORT) {
		Toast.makeText(this, message, duration).show()
	}

	/**
	 * Displays a toast message to the user.
	 *
	 * @param[resId] The string resource id of the message to be displayed to the user
	 * @param[duration] How long to display the message.  Either [Toast.LENGTH_SHORT] or [Toast.LENGTH_LONG]
	 */
	fun showToastMessage(@StringRes resId: Int, @ToastDuration duration: Int = Toast.LENGTH_SHORT) {
		Toast.makeText(this, resId, duration).show()
	}

	/**
	 * Hide the soft keyboard shown on the screen.
	 *
	 * @param[view] the view that is currently accepting input
	 */
	fun hideSoftKeyboard(view: View) {
		val imm: InputMethodManager =
			getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
		imm.hideSoftInputFromWindow(view.windowToken, 0)
	}

	@IntDef(value = [Toast.LENGTH_SHORT, Toast.LENGTH_LONG])
	@Retention(AnnotationRetention.SOURCE)
	private annotation class ToastDuration

	@IntDef(
		BaseTransientBottomBar.LENGTH_INDEFINITE,
		BaseTransientBottomBar.LENGTH_SHORT,
		BaseTransientBottomBar.LENGTH_LONG
	)
	@IntRange(from = 1)
	@Retention(AnnotationRetention.SOURCE)
	private annotation class SnackbarDuration
}
