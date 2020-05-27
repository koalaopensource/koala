package io.github.koalaopensource.koala.businesslogic

import android.os.Handler
import androidx.lifecycle.ViewModel

/**
 * A ViewModel with helper properties and methods.
 *
 * @property[viewModelHandler] the handler to be used across this view model scope, whose callbacks
 *                             and messages are automatically removed on [onCleared]
 */
abstract class BaseViewModel : ViewModel() {
    val viewModelHandler = Handler()

    /** Removes all callbacks and messages of [viewModelHandler] */
    fun removeAllCallbacksAndMessages() {
        viewModelHandler.removeCallbacksAndMessages(null)
    }

    override fun onCleared() {
        super.onCleared()
        removeAllCallbacksAndMessages()
    }

}
