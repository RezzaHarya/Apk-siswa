package com.example.murid3314

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.button.MaterialButton

class Nilai : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nilai)

        val btn_uts:MaterialButton = findViewById(R.id.btn_uts)
        val btn_uas:MaterialButton = findViewById(R.id.btn_uas)
        val fcv_nilai:FragmentContainerView = findViewById(R.id.fcv_nilai)

        btn_uts.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(fcv_nilai.id, Uts()).commit()
        }
        btn_uas.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(fcv_nilai.id, Uas()).commit()
        }
    }
}