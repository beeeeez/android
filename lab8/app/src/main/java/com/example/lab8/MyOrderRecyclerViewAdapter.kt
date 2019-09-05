package com.example.lab8

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.example.lab8.OrderFragment.OnListFragmentInteractionListener
import com.example.lab8.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.fragment_order2.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyOrderRecyclerViewAdapter(
    private val mValues: List<Order>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyOrderRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Order
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
          mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_order2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mFname.text = item.fname
        holder.mLname.text =item.lname
        holder.mNumberBars.text = item.number.toString()

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mFname: TextView = mView.fname
        val mLname: TextView = mView.lname
        val mNumberBars: TextView = mView.numbars

        override fun toString(): String {
            return super.toString() + " '" + mFname.text + " " + mLname.text + " - number of bars : " +mNumberBars+"'"
        }
    }
}
