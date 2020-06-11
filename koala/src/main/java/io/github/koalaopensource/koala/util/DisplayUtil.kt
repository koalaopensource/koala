package io.github.koalaopensource.koala.util

import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.roundToInt

private val pxInOneDp =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, Resources.getSystem().displayMetrics)

/**
 * Converts a number from DP to pixel dimension.
 *
 * @return number in pixels
 *
 * @receiver [Number]
 */
fun Number.toPx(): Int {
  return (this.toDouble() * pxInOneDp).roundToInt()
}

/**
 * Converts a number from pixel to DP dimension.
 *
 * @return number in DP
 *
 * @receiver [Number]
 */
fun Number.toDp(): Int {
  return (this.toDouble() / pxInOneDp).roundToInt()
}
