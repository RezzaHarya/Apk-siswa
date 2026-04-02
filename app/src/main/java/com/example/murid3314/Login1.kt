package com.example.murid3314

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Login1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login1)

        val edt_nis: EditText = findViewById(R.id.edt_nis)
        val edt_password: EditText = findViewById(R.id.edt_password)
        val btn_login: Button = findViewById(R.id.btn_login)

        btn_login.setOnClickListener {
            //dapatkan inputan nis dan password
            val isi_nis = edt_nis.text.toString()
            val isi_password = edt_password.text.toString()

            val minta = Volley.newRequestQueue(this)
            val mintadata: StringRequest = object : StringRequest(
                Request.Method.POST,
                Backend().url_login,
                Response.Listener<String> { response ->

                    val response = JSONObject(response)
                    if (response.getString("hasil")=="sukses") {
                        //proses menyimpan data ke dalam session
                        var session = getSharedPreferences("siswa", MODE_PRIVATE).edit()
                        session.putString("nis", response.getJSONObject("data").getString("nis"))
                        session.putString("nama_siswa", response.getJSONObject("data").getString("nama_siswa"))
                        session.putString("foto_siswa", response.getJSONObject("data").getString("foto_siswa"))
                        session.commit()
                        //pindah hal dari hal ini ke hal dashboard
                        val pindah:Intent = Intent(this, Dashboard::class.java)
                        startActivity(pindah)
                    } else {
                        //pindah hal dari hal ini ke hal login
                        val pindah:Intent = Intent(this, Login1::class.java)
                        startActivity(pindah)
                    }
                },
                Response.ErrorListener { eek ->
                    Log.d("eekror", eek.toString())
                }
            ){
                override fun getParams(): MutableMap<String, String>? {
                    val bawaan:MutableMap<String, String> = HashMap()
                    bawaan.put("kode", "amikomoke")
                    bawaan.put("nis", isi_nis)
                    bawaan.put("password_siswa", isi_password)
                    return bawaan
                }
            }
            minta.add(mintadata)
        }
    }
}

