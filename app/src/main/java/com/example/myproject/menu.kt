package com.example.myproject


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction

/**
 * A simple [Fragment] subclass.
 */
class menu : Fragment() {

    private var menu1 : TextView? = null
    private var menu2 : TextView? = null
    private var Mylist : Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_menu, container, false)
        menu1 = view.findViewById<TextView>(R.id.menu1)
        menu2 = view.findViewById<TextView>(R.id.menu2)
        Mylist = view.findViewById<Button>(R.id.cart)

        menu1!!.setOnClickListener{
            val recycler_view = Recycler_view().newInstance("1")

            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, recycler_view,"fragment_recycler_view")
            transaction.addToBackStack("fragment_recycler_view")
            transaction.commit()
        }
        menu2!!.setOnClickListener{
            val recycler_view = Recycler_view().newInstance("2")
            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, recycler_view,"fragment_recycler_view")
            transaction.addToBackStack("fragment_recycler_view")
            transaction.commit()
        }
        Mylist!!.setOnClickListener {
            val ListData = listdata()
            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, ListData,"fragment_listdata")
            transaction.addToBackStack("fragment_listdata")
            transaction.commit()
        }

        return view
    }


}
