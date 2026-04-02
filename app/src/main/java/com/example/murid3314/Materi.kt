package com.example.murid3314

import android.os.Bundle
import android.util.Log
import android.widget.GridView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Materi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.materi)

        val gv_materi:GridView = findViewById(R.id.gv_materi)

        var juduls:MutableList<String> = mutableListOf()
        var namas:MutableList<String> = mutableListOf()

        val minta = Volley.newRequestQueue(this)
        val mintadata:StringRequest = object : StringRequest (
            Request.Method.POST,
            Backend().url_materi,
            Response.Listener<String> { response ->

                Log.d("hasil", response.toString())

                val dataarray = JSONObject(response).getJSONArray("data")
                for (i in 0 until dataarray.length()) {
                    val jdl = dataarray.getJSONObject(i).getString("judul_materi")
                    val tgl = dataarray.getJSONObject(i).getString("nama_guru")

                    juduls.add(jdl)
                    namas.add(tgl)
                }
                var perulangandata = Materi_item (this, juduls, namas)
                gv_materi.adapter = perulangandata
            },
            Response.ErrorListener { error ->
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val bawaan:MutableMap<String, String> = HashMap()
                bawaan.put("kode", "amikomoke")

                return bawaan
            }
        }
        minta.add(mintadata)
    }
}
