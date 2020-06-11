package io.github.koalaopensource.koala.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.VectorDrawable
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat

/**
 * Gets the bitmap object of the vector drawable resource scaled to the given measurement.
 *
 * If [measurementPx] is provided, it is used to scale the vector drawable keeping it as width or
 * height depending upon [measurement].
 *
 * @param[id] vector drawable resource to be used use
 * @param[measurementPx] the size in pixels to scale to
 * @param[measurement] the measurement type of [measurementPx]
 *
 * @return bitmap containing the vector drawable
 * @throws Resources.NotFoundException if the given resource does not exist
 * @throws IllegalArgumentException if the given resource id does not correspond to a vector
 * drawable
 *
 * @receiver [Context]
 */
fun Context.getBitmapForVectorDrawable(
    @DrawableRes id: Int, measurementPx: Int = -1, measurement: Measurement = Measurement.HEIGHT
): Bitmap {
  val vectorDrawable =
      ResourcesCompat.getDrawable(resources, id, null) ?: throw Resources.NotFoundException()

  if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
      vectorDrawable !is VectorDrawable) || vectorDrawable !is VectorDrawableCompat) {
    throw IllegalArgumentException()
  }
  var bitmapHeight = vectorDrawable.intrinsicHeight
  var bitmapWidth = vectorDrawable.intrinsicWidth

  if (measurementPx >= 0) {
    vectorDrawable.apply {
      when (measurement) {
        Measurement.HEIGHT -> {
          bitmapHeight = measurementPx
          bitmapWidth = intrinsicWidth * bitmapHeight / intrinsicHeight
        }
        Measurement.WIDTH -> {
          bitmapWidth = measurementPx
          bitmapHeight = intrinsicHeight * bitmapWidth / intrinsicWidth
        }
      }
    }
  }

  val bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888)

  val canvas = Canvas(bitmap)
  vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
  vectorDrawable.draw(canvas)

  return bitmap
}

/** An enum class for different types of measurement */
enum class Measurement {
  HEIGHT,
  WIDTH
}
