package com.example.murid3314

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class Pengumuman_item (val halaman:Pengumuman, val juduls:MutableList<String>, val tanggals:MutableList<String>) : BaseAdapter () {

    override fun getCount(): Int {
        return juduls.size
    }

    override fun getItem(posisi: Int): Any {
        return juduls.get(posisi)
    }

    override fun getItemId(posisi: Int): Long {
        return posisi.toLong()
    }

    override fun getView(posisi: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(halaman).inflate (R.layout.pengumuman_item, parent, false)
        val tv_judul:TextView = view.findViewById(R.id.tv_judul)
        val tv_tanggal:TextView = view.findViewById(R.id.tv_tanggal)
        tv_judul.text = juduls.get(posisi)
        tv_tanggal.text = tanggals.get(posisi)
        return view
    }
}
