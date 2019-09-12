package com.example.lab8

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_top.*
import kotlinx.android.synthetic.main.fragment_top.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [topFrag.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [topFrag.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class topFrag : Fragment() {

    var jimmy: View? = null
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val view =  inflater.inflate(R.layout.fragment_top, container, false)
        jimmy=view
        val btn = view.saveButton
        btn.setOnClickListener { view
            val timmy: Boolean
            if (view.shipbox.checkedRadioButtonId == view.nRadio.id) {
                timmy = false
            } else {
                timmy = true
            }

            val jimmy = Order()
            jimmy.fname=view.fbox.text.toString()
            jimmy.lname=view.lbox.text.toString()
            jimmy.number=view.numbox.text.toString().toInt()
            jimmy.type=view.typebox.selectedItem.toString()
            jimmy.shipping=timmy
           view.fbox.text.clear()
            view.lbox.text.clear()
            view.numbox.text.clear()
            view.typebox.setSelection(0)
            view.shipbox.clearCheck()

            onButtonPressed(jimmy)


        }
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(order: Order) {
        listener?.onFragmentInteraction(order)
    }
    fun setValues(order:Order){
      jimmy!!.fbox.setText(order.fname)
        jimmy!!.lbox.setText(order.lname)
        jimmy!!.numbox.setText(order.number.toString())
        jimmy!!.lbox.setText(order.lname)
        when(order.type){
            "Milk Chocolate" ->{jimmy!!.typebox.setSelection(0)} //he wanted get position --
            "Dark Chocolate" ->{jimmy!!.typebox.setSelection(1)}
            "White Chocolate" ->{jimmy!!.typebox.setSelection(2)}
            else -> {jimmy!!.typebox.setSelection(0)}
        }
        if(order.shipping==true){
            jimmy!!.eRadio.isChecked=true
            jimmy!!.nRadio.isChecked=false
        }
        else{
            jimmy!!.nRadio.isChecked=true
            jimmy!!.eRadio.isChecked=false
        }



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(order: Order)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment topFrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            topFrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}
