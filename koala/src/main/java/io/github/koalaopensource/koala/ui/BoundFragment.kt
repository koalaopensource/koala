package io.github.koalaopensource.koala.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * A fragment that is assured to be bound to a specific activity type.
 *
 * @param[T] the activity type for this fragment to be bound to
 */
abstract class BoundFragment<T : FragmentActivity> : Fragment() {

  /**
   * Gets the activity bound to this fragment.
   *
   * Uses an unchecked cast to cast [requireActivity] to the type [T] due to type erasure.
   *
   * @return the activity bound to this fragment
   * @throws ClassCastException if [T] is not the activity that created this fragment
   *
   * @see <a href="https://kotlinlang.org/docs/reference/generics.html#type-erasure">Type
   * Erasure</a>
   */
  protected fun getBoundActivity(): T {
    @Suppress("UNCHECKED_CAST")
    return requireActivity() as T
  }
}
