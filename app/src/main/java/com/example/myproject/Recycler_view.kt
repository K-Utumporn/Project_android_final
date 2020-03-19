package com.example.myproject


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import layout.MyRecyclerAdapter
import org.json.JSONArray
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class Recycler_view : Fragment() {
    private var menu : String? = null

    fun newInstance(menu: String): Recycler_view {
        val fragment = Recycler_view()
        val bundle = Bundle().also {
            it.putString("menu", menu)
        }

        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recycler_view, container, false)

        var jsonString:String = " "
        var jsonArray : JSONArray ? = null // take array from load json
        if(menu =="1"){
            jsonString  = loadJsonFromAsset("newbooks.json", activity!!.baseContext).toString()
            val json = JSONObject(jsonString)
            jsonArray = json.getJSONArray("newbooks")
        }else if (menu =="2"){
            jsonString  = loadJsonFromAsset("underacozy.json", activity!!.baseContext).toString()
            val json = JSONObject(jsonString)
            jsonArray = json.getJSONArray("underacozy")
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.recyLayout)



        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity!!.baseContext)
        recyclerView.layoutManager = layoutManager


        val adapter = MyRecyclerAdapter(activity!!,jsonArray!!)
        recyclerView.adapter = adapter


        return view
    }

    private fun loadJsonFromAsset(filename: String, context: Context): String? {
        val json: String?

        try {
            val inputStream = context.assets.open(filename)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: java.io.IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            menu = bundle.getString("menu").toString()

        }
    }
}
