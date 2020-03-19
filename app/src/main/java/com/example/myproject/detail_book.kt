package com.example.myproject


import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 */
class detail_book : Fragment() {

        private var _title_:String?=null
    private var _detail_:String?=null
    private var _img_:String?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_book, container, false)

        val layout_title =view?.findViewById<TextView>(R.id.title)
        val layout_detail =view?.findViewById<TextView>(R.id.detail)
        val layout_img =view.findViewById<ImageView>(R.id.image)
        layout_title?.text = this._title_
        layout_detail?.text = this._detail_
        Glide.with(this).load(_img_).into(layout_img);

        val list = view.findViewById<Button>(R.id.list)
        //ประกาศตัวแปร DatabaseReference รับค่า Instance และอ้างถึง path ที่เราต้องการใน database
        val mRootRef = FirebaseDatabase.getInstance().getReference()

        //อ้างอิงไปที่ path ที่เราต้องการจะจัดการข้อมูล ตัวอย่างคือ users และ messages
        val mCartRef = mRootRef.child("cart")

        list.setOnClickListener {

            var user = FirebaseAuth.getInstance().currentUser

            if(user != null){
                var mUserIdRef = mCartRef.child(user!!.uid)
                var postValue: HashMap<String, Any> = HashMap()
                postValue["title"] = this._title_!!
                postValue["description"] = this._detail_!!
                postValue["image"] = _img_!!
                mUserIdRef.push().setValue(postValue)
            }else{
                var mUserIdRef = mCartRef.child("s60160104")
                var postValue: HashMap<String, Any> = HashMap()
                postValue["title"] = this._title_!!
                postValue["description"] = this._detail_!!
                postValue["image"] = _img_!!
                mUserIdRef.push().setValue(postValue)
            }
            //setValue() เป็นการ write หรือ update ข้อมูล ไปยัง path ที่เราอ้างถึงได้ เช่น users/<user-id>/<username>
            //mCartRef.child("id-60160104").setValue("Thananan")
            val builder =
                AlertDialog.Builder(this.context)
            builder.setMessage("You want to add to the list.\n")
            builder.setPositiveButton(
                "OK"
            ) { dialog, id ->
                Toast.makeText(this.context , "Successfully added", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }


        return view
    }
    fun newInstance(_title_: String, _detail_: String, _img_: String): detail_book {
        val fragment = detail_book()
        val bundle = Bundle()
        bundle.putString("_title", _title_)
        bundle.putString("_detail", _detail_)
        bundle.putString("_img", _img_)
        fragment.setArguments(bundle)
        return fragment
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            _title_ = bundle.getString("_title").toString()
            _detail_ = bundle.getString("_detail").toString()
            _img_ = bundle.getString("_img").toString()

        }
    }



}
