package com.example.seminario_3_pm_vanesa_nunez_sierra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun iniciarActividadEjercicio1 (view : View){
        val intent = Intent(this, Ejercicio_1::class.java)
        startActivity(intent)
    }
}