package io.github.koalaopensource.koala.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple adapter for RecyclerView.
 *
 * This is used in cases where you just need to quickly display a list of items with a custom view
 * without going through the hassle of creating a separate adapter for it yourself.
 *
 * @param[T] the type of each item in the item array
 * @param[itemLayoutRes] the layout resource of the view corresponding to each item
 * @param[bindView] this method is called when [onBindViewHolder] is called, and is used to populate the view according to the item's data
 */
class SimpleDisplayAdapter<T>(
	@LayoutRes private val itemLayoutRes: Int,
	private val itemArrayList: ArrayList<T>,
	private val bindView: ((View, T) -> Unit)
) : RecyclerView.Adapter<SimpleDisplayAdapter<T>.SimpleDisplayViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleDisplayViewHolder {
		return SimpleDisplayViewHolder(
			LayoutInflater.from(parent.context)
				.inflate(itemLayoutRes, parent, false)
		)
	}

	override fun getItemCount() = itemArrayList.size

	override fun onBindViewHolder(holder: SimpleDisplayViewHolder, position: Int) {
		bindView(holder.itemView, itemArrayList[position])
	}

	/**
	 * A view holder that just... holds the view
	 */
	inner class SimpleDisplayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
